package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.dtos.Response.ProductResponse;

import java.util.List;

public interface ProductServiceInterface {
    ProductResponse addProduct(Product product);
    ProductResponse updateProduct(Product product);
    void deleteProduct(String productId);
    ProductResponse getProductById(String productId);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(String productName);
}
