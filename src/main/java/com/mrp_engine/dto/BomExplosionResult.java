package com.mrp_engine.dto;

public class BomExplosionResult {

	private Long itemId;
	
    private String itemName;
    
    private String itemType;
    
    private String unitOfMeasure;
    
    private Double requiredQuantity;

    public BomExplosionResult() {}

    public BomExplosionResult(Long itemId, String itemName,
                               String itemType, String unitOfMeasure,
                               Double requiredQuantity) {
        this.itemId           = itemId;
        this.itemName         = itemName;
        this.itemType         = itemType;
        this.unitOfMeasure    = unitOfMeasure;
        this.requiredQuantity = requiredQuantity;
    }

    // Getters
    public Long getItemId() { 
    	return itemId; 
    }
    
    public String getItemName() { 
    	return itemName; 
    }
    
    public String getItemType() { 
    	return itemType; 
    }
    
    public String getUnitOfMeasure() { 
    	return unitOfMeasure; 
    }
    
    public Double getRequiredQuantity() { 
    	return requiredQuantity; 
    }

    
    // Setters
    public void setItemId(Long itemId) {
    	this.itemId = itemId; 
    }
    
    public void setItemName(String itemName) { 
    	this.itemName = itemName; 
    }
    
    public void setItemType(String itemType) { 
    	this.itemType = itemType; 
    }
    
    public void setUnitOfMeasure(String unitOfMeasure) { 
    	this.unitOfMeasure = unitOfMeasure; 
    }
    
    public void setRequiredQuantity(Double requiredQuantity) { 
    	this.requiredQuantity = requiredQuantity; 
    }
	
}
