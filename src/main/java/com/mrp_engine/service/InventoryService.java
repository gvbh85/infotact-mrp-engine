package com.mrp_engine.service;

import com.mrp_engine.entity.InventoryStatus;
import com.mrp_engine.entity.Item;
import com.mrp_engine.exception.ResourceNotFoundException;
import com.mrp_engine.repository.InventoryRepository;
import com.mrp_engine.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository      = itemRepository;
    }

    // Get all inventory records
    public List<InventoryStatus> getAllInventory() {
        return inventoryRepository.findAll();
    }

    // Get inventory for a specific item
    public InventoryStatus getInventoryByItemId(Long itemId) {
        return inventoryRepository.findByItemId(itemId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Inventory record for item id " + itemId, itemId));
    }

    // Create initial inventory record for an item
    public InventoryStatus createInventory(Long itemId, Double onHandQuantity,
                                           Double reorderLevel) {

        if (inventoryRepository.existsByItemId(itemId)) {
            throw new IllegalArgumentException(
                "Inventory record already exists for item id: " + itemId);
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Item", itemId));

        InventoryStatus inventory =
                new InventoryStatus(item, onHandQuantity, reorderLevel);

        return inventoryRepository.save(inventory);
    }

    // Update on-hand quantity for an item
    public InventoryStatus updateOnHandQuantity(Long itemId, Double newQuantity) {

        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                "On-hand quantity cannot be negative.");
        }

        InventoryStatus inventory = getInventoryByItemId(itemId);
        inventory.setOnHandQuantity(newQuantity);
        return inventoryRepository.save(inventory);
    }

    // Helper used by MrpService later — returns 0 if no inventory record exists
    public Double getOnHandQuantityOrZero(Long itemId) {
        return inventoryRepository.findByItemId(itemId)
                .map(InventoryStatus::getOnHandQuantity)
                .orElse(0.0);
    }

    
}