package com.mrp_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrp_engine.entity.Item;
import com.mrp_engine.enums.ItemType;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	// Find all items of a specific type (e.g. all RAW_MATERIALs)
    List<Item> findByType(ItemType type);

    // Find all direct children of a parent item
    List<Item> findByParentItemId(Long parentId);

    // Find all top-level items (no parent = finished goods)
    List<Item> findByParentItemIsNull();
	
}