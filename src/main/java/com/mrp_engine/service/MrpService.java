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
import java.util.List;
import java.util.Map;

@Service
public class MrpService {
	
	private final ItemRepository itemRepository;
	
    private final BomLinkRepository bomLinkRepository;

    public MrpService(ItemRepository itemRepository,
                      BomLinkRepository bomLinkRepository) {
        this.itemRepository    = itemRepository;
        this.bomLinkRepository = bomLinkRepository;
    }

    /**
     * Main entry point — takes a productId and target quantity,
     * returns a flat list of all raw materials needed.
     */
    public List<BomExplosionResult> explodeBom(Long productId,
                                                Double targetQuantity) {

        // Validate product exists
        Item product = itemRepository.findById(productId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Item", productId));

        // Map to accumulate quantities: itemId → totalQuantityNeeded
        Map<Long, Double> requirementsMap = new HashMap<>();

        // TODO Day 7: recursive traversal goes here
        // explodeRecursive(productId, targetQuantity, requirementsMap);

        // TODO Day 7: convert map to result list
        List<BomExplosionResult> results = new ArrayList<>();

        return results;
    }

    /**
     * Recursive helper method — will be implemented on Day 7.
     * Traverses BOM tree and accumulates quantities in the map.
     */
    private void explodeRecursive(Long itemId, Double quantity, Map<Long, Double> requirementsMap) {
        // TODO Day 7 implementation:
        // 1. Get all BomLink children of this itemId
        // 2. For each child: childQty = quantity × link.quantityRequired
        // 3. If child has no children → add to requirementsMap
        // 4. If child has children → recurse with childQty
    }
	
}
