package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.data.repositories.ProductRepositories;
import com.obswrldEcommerceApp.dtos.Request.ProductRequest;
import com.obswrldEcommerceApp.dtos.Response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductServiceInterface{

    private final ProductRepositories productRepositories;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .
                .build();
    }
}
