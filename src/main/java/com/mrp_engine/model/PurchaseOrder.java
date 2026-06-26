package com.mrp_engine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer orderQuantity;

    @Column(nullable = false)
    private String status; // e.g., PENDING, APPROVED

    private LocalDateTime orderDate;

    public PurchaseOrder() {}

    public PurchaseOrder(Item item, Integer orderQuantity, String status, LocalDateTime orderDate) {
        this.item = item;
        this.orderQuantity = orderQuantity;
        this.status = status;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public Integer getOrderQuantity() { return orderQuantity; }
    public void setOrderQuantity(Integer orderQuantity) { this.orderQuantity = orderQuantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}