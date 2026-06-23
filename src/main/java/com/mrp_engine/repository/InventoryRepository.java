package com.mrp_engine.repository;

import com.mrp_engine.entity.InventoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryStatus, Long> {

    // Find inventory record by the related item's ID
    Optional<InventoryStatus> findByItemId(Long itemId);

    // Check if inventory record already exists for an item
    boolean existsByItemId(Long itemId);
    
    
}