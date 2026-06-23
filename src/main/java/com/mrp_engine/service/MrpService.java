package com.mrp_engine.service;

import com.mrp_engine.dto.BomExplosionResult;
import com.mrp_engine.entity.BomLink;
import com.mrp_engine.entity.Item;
import com.mrp_engine.exception.ResourceNotFoundException;
import com.mrp_engine.repository.BomLinkRepository;
import com.mrp_engine.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MrpService {

    private final ItemRepository      itemRepository;
    private final BomLinkRepository   bomLinkRepository;
    private final InventoryService    inventoryService;   // NEW dependency

    public MrpService(ItemRepository itemRepository,
                      BomLinkRepository bomLinkRepository,
                      InventoryService inventoryService) {   // NEW parameter
        this.itemRepository    = itemRepository;
        this.bomLinkRepository = bomLinkRepository;
        this.inventoryService  = inventoryService;
    }

    public List<BomExplosionResult> explodeBom(Long productId,
                                                Double targetQuantity) {

        if (targetQuantity == null || targetQuantity <= 0) {
            throw new IllegalArgumentException(
                "Target quantity must be greater than zero.");
        }

        itemRepository.findById(productId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Item", productId));

        Map<Long, Double> requirementsMap = new HashMap<>();
        Set<Long> visitedItems = new HashSet<>();

        explodeRecursive(productId, targetQuantity,
                         requirementsMap, visitedItems);

        List<BomExplosionResult> results = new ArrayList<>();

        for (Map.Entry<Long, Double> entry : requirementsMap.entrySet()) {

            Long   itemId           = entry.getKey();
            Double grossRequirement = entry.getValue();

            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() ->
                        new ResourceNotFoundException("Item", itemId));

            // fetch current stock for this raw material
            Double onHandQuantity =
                    inventoryService.getOnHandQuantityOrZero(itemId);

            // calculate Net Requirement
            Double netRequirement =
                    Math.max(0, grossRequirement - onHandQuantity);

            results.add(new BomExplosionResult(
                    item.getId(),
                    item.getName(),
                    item.getType().name(),
                    item.getUnitOfMeasure(),
                    grossRequirement,
                    onHandQuantity,
                    netRequirement
            ));
        }

        return results;
    }

    private void explodeRecursive(Long itemId,
                                   Double quantity,
                                   Map<Long, Double> requirementsMap,
                                   Set<Long> visitedItems) {

        if (visitedItems.contains(itemId)) {
            throw new IllegalArgumentException(
                "Circular reference detected in BOM for item id: " + itemId);
        }

        visitedItems.add(itemId);

        List<BomLink> children =
                bomLinkRepository.findByParentItemId(itemId);

        if (children.isEmpty()) {
            requirementsMap.merge(itemId, quantity, Double::sum);
        } else {
            for (BomLink link : children) {
                Long   childId  = link.getChildItem().getId();
                Double childQty = quantity * link.getQuantityRequired();
                explodeRecursive(childId, childQty,
                                 requirementsMap, visitedItems);
            }
        }

        visitedItems.remove(itemId);
    }
}