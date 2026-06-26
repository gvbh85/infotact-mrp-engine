package com.mrp_engine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", unique = true)
    private Item item;

    @Column(nullable = false)
    private Integer quantityOnHand;

    @Column(nullable = false)
    private Integer safetyStock;

    // Constructors
    public Inventory() {}

    public Inventory(Item item, Integer quantityOnHand, Integer safetyStock) {
        this.item = item;
        this.quantityOnHand = quantityOnHand;
        this.safetyStock = safetyStock;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public Integer getQuantityOnHand() { return quantityOnHand; }
    public void setQuantityOnHand(Integer quantityOnHand) { this.quantityOnHand = quantityOnHand; }
    public Integer getSafetyStock() { return safetyStock; }
    public void setSafetyStock(Integer safetyStock) { this.safetyStock = safetyStock; }
}