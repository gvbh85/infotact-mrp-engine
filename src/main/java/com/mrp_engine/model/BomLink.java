package com.mrp_engine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bom_links")
public class BomLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Item parentItem; // Must match the mappedBy property in Item.java

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Item childItem;

    @Column(nullable = false)
    private Integer quantityPerParent;

    // Getters, Setters, and Constructors...
    public Item getParentItem() { return parentItem; }
    public Item getChildItem() { return childItem; }
    public Integer getQuantityPerParent() { return quantityPerParent; }
}