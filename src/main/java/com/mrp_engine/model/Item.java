package com.mrp_engine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(name = "item_type", nullable = false)
    private String itemType;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // 🚀 NEW: This connects the item to its sub-components for the recursive explosion logic!
    // @JsonIgnore prevents infinite loops when Spring converts your data into JSON for the frontend.
    @OneToMany(mappedBy = "parentItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BomLink> bomLinks = new ArrayList<>();

    // Default No-Args Constructor (Required by JPA)
    public Item() {}

    // Getters and Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // New Getter/Setter for BOM explosion loop
    public List<BomLink> getBomLinks() { return bomLinks; }
    public void setBomLinks(List<BomLink> bomLinks) { this.bomLinks = bomLinks; }
}