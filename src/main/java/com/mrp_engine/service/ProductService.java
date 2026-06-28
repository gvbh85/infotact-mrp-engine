package com.mrp_engine.service;

import java.util.List;

import com.mrp_engine.dto.request.ProductRequest;
import com.mrp_engine.dto.response.ProductResponse;

public interface ProductService {

    ProductResponse addProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getAllProducts();

}