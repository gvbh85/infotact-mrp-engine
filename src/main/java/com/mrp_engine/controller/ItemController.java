package com.mrp_engine.controller;

import com.mrp_engine.model.Item;
import com.mrp_engine.repository.ItemRepository;
import com.mrp_engine.service.MRPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "*") 
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MRPService mrpService; // Injected Service Layer

    // 1. Get all manufacturing items
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // 2. Add a new component to the system
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item savedItem = itemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }

    // 🚀 NEW: Explode a Bill of Materials recursively to calculate core requirements
    @GetMapping("/{id}/explode")
    public ResponseEntity<Map<String, Integer>> explodeBOM(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int quantity) {
        
        Map<String, Integer> requirements = mrpService.calculateMaterialRequirements(id, quantity);
        return ResponseEntity.ok(requirements);
    }
}