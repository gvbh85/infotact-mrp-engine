package com.mrp_engine.controller;
//Test comment to verify GitHub Pull Request workflow
import com.mrp_engine.model.Item;
import com.mrp_engine.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "*") // Allows React frontend connection later
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

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
}