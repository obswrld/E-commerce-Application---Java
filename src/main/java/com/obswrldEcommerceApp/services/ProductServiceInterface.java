package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.dtos.Request.ProductRequest;
import com.obswrldEcommerceApp.dtos.Response.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductServiceInterface {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(String productId, ProductRequest productRequest);
    ProductResponse getProductById(String id);
    List<ProductResponse> getAllProducts();
    void deleteProduct(String productId);
    List<ProductResponse> getProductByCategory(String category);
}
