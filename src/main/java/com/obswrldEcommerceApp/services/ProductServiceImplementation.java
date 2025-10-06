package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.data.repositories.ProductRepositories;
import com.obswrldEcommerceApp.dtos.Request.ProductRequest;
import com.obswrldEcommerceApp.dtos.Response.ProductResponse;
import com.obswrldEcommerceApp.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductServiceInterface {
    private final ProductRepositories productRepositories;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Mapper.toEntity(productRequest);
        Product saveProduct =  productRepositories.save(product);
        return Mapper.toResponse(saveProduct);
    }

    @Override
    public ProductResponse getProductById(String id){
        Product product = productRepositories.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        return Mapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts(){
        return productRepositories.findAll()
                .stream()
                .map(Mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(String id, ProductRequest productRequest){
        Product existingProduct = productRepositories.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setQuantity(productRequest.getStock());
        existingProduct.setCategory(productRequest.getCategory());
        existingProduct.setDescription(productRequest.getDescription());
        Product updatedProduct = productRepositories.save(existingProduct);
        return Mapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(String productId){
        if(!productRepositories.existsById(productId)){
            throw new RuntimeException("Product not found");
        }
        productRepositories.deleteById(productId);
    }

    @Override
    public List<ProductResponse> getProductByCategory(String category){
        return productRepositories.findByCategory(category)
                .stream()
                .map(Mapper::toResponse)
                .collect(Collectors.toList());
    }
}
