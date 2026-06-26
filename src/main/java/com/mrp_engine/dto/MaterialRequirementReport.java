package com.mrp_engine.dto;

public class MaterialRequirementReport {
    private Long itemId;
    private String itemName;
    private Integer grossRequired;
    private Integer inStock;
    private Integer netRequired;
    private String actionTaken;

    public MaterialRequirementReport(Long itemId, String itemName, Integer grossRequired, Integer inStock, Integer netRequired, String actionTaken) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.grossRequired = grossRequired;
        this.inStock = inStock;
        this.netRequired = netRequired;
        this.actionTaken = actionTaken;
    }

    // Getters
    public Long getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public Integer getGrossRequired() { return grossRequired; }
    public Integer getInStock() { return inStock; }
    public Integer getNetRequired() { return netRequired; }
    public String getActionTaken() { return actionTaken; }
}