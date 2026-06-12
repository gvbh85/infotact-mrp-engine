package com.mrp_engine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bom_link")
public class BomLink {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Parent item (e.g. Bicycle)
    @ManyToOne
    @JoinColumn(name = "parent_item_id", nullable = false)
    private Item parentItem;

    // Child item (e.g. Wheel)
    @ManyToOne
    @JoinColumn(name = "child_item_id", nullable = false)
    private Item childItem;

    // How many child items are needed per 1 parent
    @Column(nullable = false)
    private Double quantityRequired;

    // Constructors
    public BomLink() {}

    public BomLink(Item parentItem, Item childItem, Double quantityRequired) {
        this.parentItem = parentItem;
        this.childItem = childItem;
        this.quantityRequired = quantityRequired;
    }

    // Getters and Setters
    public Long getId() { 
    	return id;
    }

    public Item getParentItem() { 
    	return parentItem;
    }
    
    public void setParentItem(Item parentItem) { 
    	this.parentItem = parentItem;
    }

    public Item getChildItem() { 
    	return childItem;
    }
    public void setChildItem(Item childItem) { 
    	this.childItem = childItem;
    }

    public Double getQuantityRequired() { 
    	return quantityRequired; 
    }
    
    public void setQuantityRequired(Double quantityRequired) {
        this.quantityRequired = quantityRequired;
    }
	
}
