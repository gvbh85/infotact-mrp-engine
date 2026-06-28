package com.mrp_engine.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BomRequest {

    @NotNull
    private Long parentItemId;

    @NotNull
    private Long childItemId;

    @Positive
    private Double quantityRequired;

    public BomRequest(){}

    public Long getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(Long parentItemId) {
        this.parentItemId=parentItemId;
    }

    public Long getChildItemId() {
        return childItemId;
    }

    public void setChildItemId(Long childItemId) {
        this.childItemId=childItemId;
    }

    public Double getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(Double quantityRequired) {
        this.quantityRequired=quantityRequired;
    }

}