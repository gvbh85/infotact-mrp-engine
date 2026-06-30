package com.mrp_engine.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_status")
public class InventoryStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One-to-one with Item — each item has exactly one inventory record
    @OneToOne
    @JoinColumn(name = "item_id", nullable = false, unique = true)
    private Item item;

    @Column(nullable = false)
    private Double onHandQuantity;

    @Column(nullable = false)
    private Double reorderLevel;

    private LocalDateTime lastUpdated;

    // Constructors
    public InventoryStatus() {}

    public InventoryStatus(Item item, Double onHandQuantity, Double reorderLevel) {
        this.item           = item;
        this.onHandQuantity = onHandQuantity;
        this.reorderLevel   = reorderLevel;
        this.lastUpdated    = LocalDateTime.now();
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

    public Double getOnHandQuantity() { 
    	return onHandQuantity; 
    }
    
    public void setOnHandQuantity(Double onHandQuantity) {
        this.onHandQuantity = onHandQuantity;
        this.lastUpdated = LocalDateTime.now();
    }

    public Double getReorderLevel() { 
    	return reorderLevel; 
    }
    
    public void setReorderLevel(Double reorderLevel) { 
    	this.reorderLevel = reorderLevel; 
    }

    public LocalDateTime getLastUpdated() { 
    	return lastUpdated; 
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) { 
    	this.lastUpdated = lastUpdated; 
    }
    
    
}