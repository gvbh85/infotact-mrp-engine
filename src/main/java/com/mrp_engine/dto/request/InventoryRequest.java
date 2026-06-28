package com.mrp_engine.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class InventoryRequest {

    @NotNull
    private Long itemId;

    @NotNull
    @PositiveOrZero
    private Double onHandQuantity;

    @NotNull
    @PositiveOrZero
    private Double reorderLevel;

    public InventoryRequest(){}

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId=itemId;
    }

    public Double getOnHandQuantity() {
        return onHandQuantity;
    }

    public void setOnHandQuantity(Double onHandQuantity) {
        this.onHandQuantity=onHandQuantity;
    }

    public Double getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Double reorderLevel) {
        this.reorderLevel=reorderLevel;
    }

}