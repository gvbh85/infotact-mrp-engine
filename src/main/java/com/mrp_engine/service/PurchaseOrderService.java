package com.mrp_engine.service;

import com.mrp_engine.entity.Item;
import com.mrp_engine.entity.PurchaseOrder;
import com.mrp_engine.enums.PurchaseOrderStatus;
import com.mrp_engine.exception.ResourceNotFoundException;
import com.mrp_engine.repository.ItemRepository;
import com.mrp_engine.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ItemRepository itemRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
                                ItemRepository itemRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.itemRepository          = itemRepository;
    }

    /**
     * Auto-generate a PO for an item if Net Requirement > 0.
     * Skips creation if a PENDING PO already exists for the same item,
     * preventing duplicate orders on repeated MRP runs.
     */
    public void autoGeneratePurchaseOrder(Long itemId, Double netRequirement) {

        if (netRequirement == null || netRequirement <= 0) {
            return;   // No shortage — nothing to order
        }

        boolean alreadyPending = purchaseOrderRepository
                .existsByItemIdAndStatus(itemId, PurchaseOrderStatus.PENDING);

        if (alreadyPending) {
            return;   // Avoid creating duplicate pending POs
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Item", itemId));

        PurchaseOrder po = new PurchaseOrder(
                item, netRequirement, "Default Supplier");

        purchaseOrderRepository.save(po);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public List<PurchaseOrder> getPurchaseOrdersByStatus(PurchaseOrderStatus status) {
        return purchaseOrderRepository.findByStatus(status);
    }

    public PurchaseOrder approvePurchaseOrder(Long id) {
        PurchaseOrder po = purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("PurchaseOrder", id));

        po.setStatus(PurchaseOrderStatus.APPROVED);
        return purchaseOrderRepository.save(po);
    }

    public PurchaseOrder rejectPurchaseOrder(Long id) {
        PurchaseOrder po = purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("PurchaseOrder", id));

        po.setStatus(PurchaseOrderStatus.REJECTED);
        return purchaseOrderRepository.save(po);
    }
    
}