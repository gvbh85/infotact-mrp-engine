package com.mrp_engine.controller;

import com.mrp_engine.entity.InventoryStatus;
import com.mrp_engine.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // GET all inventory records
    @GetMapping
    public ResponseEntity<List<InventoryStatus>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    // GET inventory for a specific item
    @GetMapping("/{itemId}")
    public ResponseEntity<InventoryStatus> getInventoryByItemId(
            @PathVariable Long itemId) {
        return ResponseEntity.ok(inventoryService.getInventoryByItemId(itemId));
    }

    // POST create initial inventory record
    // Body: { "itemId": 7, "onHandQuantity": 5000, "reorderLevel": 1000 }
    @PostMapping
    public ResponseEntity<InventoryStatus> createInventory(
            @RequestBody Map<String, Object> request) {

        Long   itemId         = Long.valueOf(request.get("itemId").toString());
        Double onHandQuantity = Double.valueOf(request.get("onHandQuantity").toString());
        Double reorderLevel   = Double.valueOf(request.get("reorderLevel").toString());

        InventoryStatus created = inventoryService.createInventory(
                itemId, onHandQuantity, reorderLevel);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT update on-hand quantity
    // Body: { "onHandQuantity": 8000 }
    @PutMapping("/{itemId}")
    public ResponseEntity<InventoryStatus> updateInventory(
            @PathVariable Long itemId,
            @RequestBody Map<String, Object> request) {

        Double newQuantity =
                Double.valueOf(request.get("onHandQuantity").toString());

        InventoryStatus updated =
                inventoryService.updateOnHandQuantity(itemId, newQuantity);

        return ResponseEntity.ok(updated);
    }

    
}