package com.mrp_engine.dto;

public class BomExplosionResult {

	private Long itemId;
	
    private String itemName;
    
    private String itemType;
    
    private String unitOfMeasure;
    
    private Double grossRequirement;
    private Double onHandQuantity;
    private Double netRequirement;

    public BomExplosionResult() {}

    public BomExplosionResult(Long itemId, String itemName,
                               String itemType, String unitOfMeasure,
                               Double grossRequirement, Double onHandQuantity,
                               Double netRequirement) {
        this.itemId           = itemId;
        this.itemName         = itemName;
        this.itemType         = itemType;
        this.unitOfMeasure    = unitOfMeasure;
        this.grossRequirement = grossRequirement;
        this.onHandQuantity   = onHandQuantity;
        this.netRequirement   = netRequirement;
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
    
    public Double getGrossRequirement() { 
    	return grossRequirement; 
    }
    
    public Double getOnHandQuantity() { 
    	return onHandQuantity; 
    }
    
    public Double getNetRequirement() { 
    	return netRequirement; 
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
    
    public void setGrossRequirement(Double grossRequirement) { 
    	this.grossRequirement = grossRequirement; 
    }
    
    public void setOnHandQuantity(Double onHandQuantity) { 
    	this.onHandQuantity = onHandQuantity; 
    }
    
    public void setNetRequirement(Double netRequirement) { 
    	this.netRequirement = netRequirement; 
    }
	
}
