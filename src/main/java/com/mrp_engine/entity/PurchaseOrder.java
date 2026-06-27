package com.mrp_engine.entity;

import com.mrp_engine.enums.PurchaseOrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double requiredQuantity;

    private String supplierName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseOrderStatus status;

    private LocalDateTime createdDate;

    // Constructors
    public PurchaseOrder() {}

    public PurchaseOrder(Item item, Double requiredQuantity, String supplierName) {
        this.item             = item;
        this.requiredQuantity = requiredQuantity;
        this.supplierName     = supplierName;
        this.status           = PurchaseOrderStatus.PENDING;
        this.createdDate      = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { 
    	return id; 
    }

    public Item getItem() { 
    	return item; 
    }
    
    public void setItem(Item item) { 
    	this.item = item; 
    }

    public Double getRequiredQuantity() { 
    	return requiredQuantity; 
    
    }
    public void setRequiredQuantity(Double requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public String getSupplierName() { 
    	return supplierName; 
    }
    
    public void setSupplierName(String supplierName) { 
    	this.supplierName = supplierName; 
    }

    public PurchaseOrderStatus getStatus() { 
    	return status; 
    }
    
    public void setStatus(PurchaseOrderStatus status) { 
    	this.status = status; 
    }

    public LocalDateTime getCreatedDate() { 
    	return createdDate; 
    }
    
    public void setCreatedDate(LocalDateTime createdDate) { 
    	this.createdDate = createdDate; 
    }
    
}