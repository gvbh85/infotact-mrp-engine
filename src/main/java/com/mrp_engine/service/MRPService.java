package com.mrp_engine.service;

import com.mrp_engine.dto.MaterialRequirementReport;
import com.mrp_engine.model.Item;
import com.mrp_engine.model.BomLink; // Added missing import
import com.mrp_engine.model.Inventory;
import com.mrp_engine.model.PurchaseOrder;
import com.mrp_engine.repository.ItemRepository;
import com.mrp_engine.repository.InventoryRepository;
import com.mrp_engine.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MRPService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    /**
     * 🚀 Part 2 Core Engine: Recursively explodes BOM to get Gross Requirements
     */
    public Map<Item, Integer> explodeBOM(Long itemId, int quantity) {
        Map<Item, Integer> grossRequirements = new HashMap<>();
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        
        if (itemOpt.isPresent()) {
            calculateRequirementsRecursive(itemOpt.get(), quantity, grossRequirements);
        }
        
        return grossRequirements;
    }

    private void calculateRequirementsRecursive(Item item, int requiredQty, Map<Item, Integer> requirementsMap) {
        // Track gross requirement for the current item
        requirementsMap.put(item, requirementsMap.getOrDefault(item, 0) + requiredQty);

        // Fixed: Using your actual getter 'getBomLinks()' instead of 'getComponents()'
        if (item.getBomLinks() != null && !item.getBomLinks().isEmpty()) {
            for (BomLink link : item.getBomLinks()) {
                int childRequiredQty = link.getQuantityPerParent() * requiredQty;
                calculateRequirementsRecursive(link.getChildItem(), childRequiredQty, requirementsMap);
            }
        }
    }
    
    /**
     * 📦 Part 3 Engine: Computes Net Requirements & Auto-Generates Procurement Contracts
     */
    @Transactional
    public List<MaterialRequirementReport> processInventoryAndProcurement(Map<Item, Integer> grossRequirements) {
        List<MaterialRequirementReport> reportList = new ArrayList<>();

        for (Map.Entry<Item, Integer> entry : grossRequirements.entrySet()) {
            Item item = entry.getKey();
            Integer grossRequired = entry.getValue();

            // Check current stock levels
            Optional<Inventory> invOpt = inventoryRepository.findByItemId(item.getId());
            int stockAvailable = invOpt.map(Inventory::getQuantityOnHand).orElse(0);

            // Calculation: Net Requirements = Gross Required - Current Stock
            int netRequired = grossRequired - stockAvailable;
            String action = "Stock Sufficient";

            if (netRequired > 0) {
                action = "Purchase Order Generated";
                // Automatically generate a real Purchase Order entity for shortages
                PurchaseOrder po = new PurchaseOrder(item, netRequired, "PENDING", LocalDateTime.now());
                purchaseOrderRepository.save(po);
            } else {
                netRequired = 0;
            }

            reportList.add(new MaterialRequirementReport(
                item.getId(), 
                item.getName(), 
                grossRequired, 
                stockAvailable, 
                netRequired, 
                action
            ));
        }
        return reportList;
    } 
}