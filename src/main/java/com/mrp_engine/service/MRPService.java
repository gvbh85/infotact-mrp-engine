package com.mrp_engine.service;

import com.mrp_engine.model.BomLink;
import com.mrp_engine.model.Item;
import com.mrp_engine.repository.BomLinkRepository;
import com.mrp_engine.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MRPService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BomLinkRepository bomLinkRepository;

    /**
     * Calculates total raw materials required for an item using recursive BOM explosion.
     */
    public Map<String, Integer> calculateMaterialRequirements(Long itemId, int quantity) {
        Map<String, Integer> totalRequirements = new HashMap<>();
        
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));
        
        // Start the recursive breakdown
        explodeBOM(item, quantity, totalRequirements);
        
        return totalRequirements;
    }

    private void explodeBOM(Item currentItem, int currentDemand, Map<String, Integer> requirementsMap) {
        // Fetch sub-components matching this parent item
        List<BomLink> childLinks = bomLinkRepository.findByParent(currentItem);

        // FIXED: Added defensive null handling. 
        // If it has NO sub-components, it's a true raw material leaf node. Record its total required count!
        if (childLinks == null || childLinks.isEmpty()) {
            requirementsMap.put(
                currentItem.getName(), 
                requirementsMap.getOrDefault(currentItem.getName(), 0) + currentDemand
            );
            return;
        }

        // FIXED: If it HAS sub-components, do NOT log the top-level parent assembly name. 
        // Loop directly over its component recipes and push deeper into the tree.
        for (BomLink link : childLinks) {
            Item childItem = link.getChildItem();
            
            // This safely maps against your active getter method inside BomLink.java
            int quantityPerParent = link.getQuantity(); 
            
            // Sub-component demand formula = Parent Demand * Component quantity required per unit
            int subDemand = currentDemand * quantityPerParent;
            
            // Tail-recursive call to explode the sub-component completely down to raw state
            explodeBOM(childItem, subDemand, requirementsMap);
        }
    }
}