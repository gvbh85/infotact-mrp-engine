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

    private final ItemRepository    itemRepository;
    private final BomLinkRepository bomLinkRepository;

    public MrpService(ItemRepository itemRepository,
                      BomLinkRepository bomLinkRepository) {
        this.itemRepository    = itemRepository;
        this.bomLinkRepository = bomLinkRepository;
    }

    /**
     * MAIN ENTRY POINT
     * Takes productId + targetQuantity
     * Returns flat list of all raw materials needed
     */
    public List<BomExplosionResult> explodeBom(Long productId,
                                                Double targetQuantity) {

        // Step 1 — Validate product exists
        itemRepository.findById(productId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Item", productId));

        // Step 2 — Map to accumulate: itemId → total quantity needed
        Map<Long, Double> requirementsMap = new HashMap<>();

        // Step 3 — Set to track visited items (circular reference guard)
        Set<Long> visitedItems = new HashSet<>();

        // Step 4 — Start recursive traversal
        explodeRecursive(productId, targetQuantity,
                         requirementsMap, visitedItems);

        // Step 5 — Convert map to result list
        List<BomExplosionResult> results = new ArrayList<>();

        for (Map.Entry<Long, Double> entry : requirementsMap.entrySet()) {

            Long   itemId   = entry.getKey();
            Double quantity = entry.getValue();

            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() ->
                        new ResourceNotFoundException("Item", itemId));

            results.add(new BomExplosionResult(
                    item.getId(),
                    item.getName(),
                    item.getType().name(),
                    item.getUnitOfMeasure(),
                    quantity
            ));
        }

        return results;
    }

    /**
     * RECURSIVE HELPER METHOD
     * Traverses BOM tree depth-first
     * Multiplies quantities as it goes deeper
     * Accumulates raw material totals in requirementsMap
     */
    private void explodeRecursive(Long itemId,
                                   Double quantity,
                                   Map<Long, Double> requirementsMap,
                                   Set<Long> visitedItems) {

        //  Circular reference guard
        if (visitedItems.contains(itemId)) {
            throw new IllegalArgumentException(
                "Circular reference detected in BOM for item id: " + itemId);
        }

        // Mark this item as visited
        visitedItems.add(itemId);

        // Get all children of this item from bom_link table
        List<BomLink> children =
                bomLinkRepository.findByParentItemId(itemId);

        if (children.isEmpty()) {
            //  BASE CASE — no children means this is a Raw Material
            // Add or accumulate quantity in the map
            requirementsMap.merge(itemId, quantity, Double::sum);

        } else {
            // ✅ RECURSIVE CASE — has children, go deeper
            for (BomLink link : children) {

                Long   childId  = link.getChildItem().getId();
                // Multiply quantity as we go deeper
                Double childQty = quantity * link.getQuantityRequired();

                // Recurse into the child
                explodeRecursive(childId, childQty,
                                 requirementsMap, visitedItems);
            }
        }

        // Unmark after processing so sibling branches
        // can visit the same item independently
        visitedItems.remove(itemId);
    }
}