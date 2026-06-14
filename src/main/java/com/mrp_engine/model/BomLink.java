package com.mrp_engine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bom_links")
public class BomLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_item_id", nullable = false)
    private Item parentItem;

    @ManyToOne
    @JoinColumn(name = "child_item_id", nullable = false)
    private Item childItem;

    @Column(name = "quantity_required", nullable = false)
    private Integer quantityRequired;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Item getParentItem() { return parentItem; }
    public void setParentItem(Item parentItem) { this.parentItem = parentItem; }
    public Item getChildItem() { return childItem; }
    public void setChildItem(Item childItem) { this.childItem = childItem; }
    public Integer getQuantityRequired() { return quantityRequired; }
    public void setQuantityRequired(Integer quantityRequired) { this.quantityRequired = quantityRequired; }
}