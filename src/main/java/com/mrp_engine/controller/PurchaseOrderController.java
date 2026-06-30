package com.mrp_engine.controller;

import com.mrp_engine.entity.PurchaseOrder;
import com.mrp_engine.enums.PurchaseOrderStatus;
import com.mrp_engine.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@CrossOrigin(origins = "*")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    // GET all purchase orders
    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }

    // GET purchase orders filtered by status
    // Example: /api/purchase-orders/status/PENDING
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PurchaseOrder>> getByStatus(
            @PathVariable String status) {

        PurchaseOrderStatus poStatus =
                PurchaseOrderStatus.valueOf(status.toUpperCase());

        return ResponseEntity.ok(
                purchaseOrderService.getPurchaseOrdersByStatus(poStatus));
    }

    // PUT approve a purchase order
    @PutMapping("/{id}/approve")
    public ResponseEntity<PurchaseOrder> approvePurchaseOrder(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                purchaseOrderService.approvePurchaseOrder(id));
    }

    // PUT reject a purchase order
    @PutMapping("/{id}/reject")
    public ResponseEntity<PurchaseOrder> rejectPurchaseOrder(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                purchaseOrderService.rejectPurchaseOrder(id));
    }

    
}