package com.mrp_engine.controller;

import com.mrp_engine.dto.MaterialRequirementReport;
import com.mrp_engine.model.Item;
import com.mrp_engine.repository.ItemRepository;
import com.mrp_engine.service.MRPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MRPService mrpService; 

    // 1. Get all manufacturing items (Dropdown list source)
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

    // 🚀 Part 2: Explode a Bill of Materials recursively to calculate GROSS requirements
    @GetMapping("/{id}/explode")
    public ResponseEntity<Map<String, Integer>> explodeBOM(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int quantity) {
        
        // 1. Fetch the raw map with complex Item objects as keys
        Map<Item, Integer> grossRequirements = mrpService.explodeBOM(id, quantity);
        
        // 2. Convert to a String-keyed map (using SKU) so Jackson can cleanly convert it to JSON
        Map<String, Integer> serializableResponse = new HashMap<>();
        for (Map.Entry<Item, Integer> entry : grossRequirements.entrySet()) {
            serializableResponse.put(entry.getKey().getSku(), entry.getValue());
        }
        
        return ResponseEntity.ok(serializableResponse);
    }

    // 📦 Part 3: Calculate NET requirements and auto-generate Purchase Orders
    @GetMapping("/{id}/procurement-report")
    public ResponseEntity<List<MaterialRequirementReport>> getProcurementReport(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int quantity) {
        
        // 1. Fetch Gross requirements map from the service layer
        Map<Item, Integer> grossRequirements = mrpService.explodeBOM(id, quantity);
        
        // 2. Feed gross requirements into net tracking processing logic
        List<MaterialRequirementReport> report = mrpService.processInventoryAndProcurement(grossRequirements);
        
        return ResponseEntity.ok(report);
    }
}