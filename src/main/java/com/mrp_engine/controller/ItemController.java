package com.mrp_engine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mrp_engine.entity.Item;
import com.mrp_engine.service.ItemService;


@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {
	
	private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // GET all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    // GET single item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    // GET only finished goods (top-level items)
    @GetMapping("/finished-goods")
    public ResponseEntity<List<Item>> getFinishedGoods() {
        return ResponseEntity.ok(itemService.getFinishedGoods());
    }

    // POST create new item
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemService.createItem(item));
    }

    // PUT update existing item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,
                                            @RequestBody Item item) {
        return ResponseEntity.ok(itemService.updateItem(id, item));
    }

    // DELETE item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted successfully.");
    }
	
}
