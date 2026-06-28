package com.mrp_engine.controller;

import com.mrp_engine.dto.request.InventoryRequest;
import com.mrp_engine.dto.request.InventoryUpdateRequest;
import com.mrp_engine.entity.InventoryStatus;
import com.mrp_engine.service.InventoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryStatus>> getAllInventory() {
        return ResponseEntity.ok(
                inventoryService.getAllInventory());
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<InventoryStatus> getInventoryByItemId(
            @PathVariable Long itemId) {

        return ResponseEntity.ok(
                inventoryService.getInventoryByItemId(itemId));
    }

    @PostMapping
    public ResponseEntity<InventoryStatus> createInventory(
            @Valid @RequestBody InventoryRequest request) {

        InventoryStatus inventory =
                inventoryService.createInventory(
                        request.getItemId(),
                        request.getOnHandQuantity(),
                        request.getReorderLevel());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventory);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<InventoryStatus> updateInventory(

            @PathVariable Long itemId,

            @Valid @RequestBody InventoryUpdateRequest request) {

        InventoryStatus inventory =
                inventoryService.updateOnHandQuantity(
                        itemId,
                        request.getOnHandQuantity());

        return ResponseEntity.ok(inventory);
    }

}