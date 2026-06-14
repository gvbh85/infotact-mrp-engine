package com.mrp_engine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bom_links")
public class BomLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MATCHES SCRIPT: parent_item_id
    @ManyToOne
    @JoinColumn(name = "parent_item_id", nullable = false) 
    private Item parent;

    // MATCHES SCRIPT: child_item_id
    @ManyToOne
    @JoinColumn(name = "child_item_id", nullable = false) 
    private Item childItem;

    // MATCHES SCRIPT: quantity_required
    @Column(name = "quantity_required", nullable = false)
    private int quantity; 

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Item getParent() { return parent; }
    public void setParent(Item parent) { this.parent = parent; }

    public Item getChildItem() { return childItem; }
    public void setChildItem(Item childItem) { this.childItem = childItem; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}