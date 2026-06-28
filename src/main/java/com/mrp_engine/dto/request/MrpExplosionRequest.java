package com.mrp_engine.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MrpExplosionRequest {

    @NotNull
    private Long productId;

    @Positive
    private Double targetQuantity;

    public MrpExplosionRequest(){}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId=productId;
    }

    public Double getTargetQuantity() {
        return targetQuantity;
    }

    public void setTargetQuantity(Double targetQuantity) {
        this.targetQuantity=targetQuantity;
    }

}