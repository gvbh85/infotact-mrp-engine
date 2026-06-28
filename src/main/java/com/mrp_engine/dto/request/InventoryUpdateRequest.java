package com.mrp_engine.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class InventoryUpdateRequest {

    @NotNull
    @PositiveOrZero
    private Double onHandQuantity;

    public InventoryUpdateRequest(){}

    public Double getOnHandQuantity() {
        return onHandQuantity;
    }

    public void setOnHandQuantity(Double onHandQuantity) {
        this.onHandQuantity=onHandQuantity;
    }

}