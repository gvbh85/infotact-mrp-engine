package com.mrp_engine.service;

// import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mrp_engine.entity.Item;
import com.mrp_engine.repository.ItemRepository;
import com.mrp_engine.exception.ResourceNotFoundException;

import java.util.List;

@Service
// @RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Get one item by ID
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", id));
    }

    // Create a new item
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Update an existing item
    public Item updateItem(Long id, Item updatedItem) {
        Item existing = getItemById(id);
        existing.setName(updatedItem.getName());
        existing.setType(updatedItem.getType());
        existing.setUnitOfMeasure(updatedItem.getUnitOfMeasure());
        existing.setDescription(updatedItem.getDescription());
        return itemRepository.save(existing);
    }

    // Delete an item
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    // Get all top-level finished goods
    public List<Item> getFinishedGoods() {
        return itemRepository.findByParentItemIsNull();
    }
	
}
