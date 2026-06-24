package com.mrp_engine.repository;

import com.mrp_engine.entity.PurchaseOrder;
import com.mrp_engine.enums.PurchaseOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    // Find all POs for a specific item
    List<PurchaseOrder> findByItemId(Long itemId);

    // Find all POs with a specific status
    List<PurchaseOrder> findByStatus(PurchaseOrderStatus status);

    // Check if a PENDING PO already exists for an item (avoid duplicates)
    boolean existsByItemIdAndStatus(Long itemId, PurchaseOrderStatus status);
    
    
}