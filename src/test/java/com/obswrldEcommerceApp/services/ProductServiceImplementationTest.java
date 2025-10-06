package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.data.repositories.ProductRepositories;
import com.obswrldEcommerceApp.dtos.Request.ProductRequest;
import com.obswrldEcommerceApp.dtos.Response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplementationTest {

    @Mock
    private ProductRepositories productRepositories;

    @InjectMocks
    private ProductServiceImplementation productServiceImplementation;

    private Product product;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        product = Product.builder()
                .productId("productId-123")
                .name("Hp-Pavilion")
                .description("Electronics")
                .category("Gadgets")
                .price(new BigDecimal("297000.00"))
                .quantity(10)
                .build();

        productRequest = ProductRequest.builder()
                .name("Hp-Pavilion")
                .description("Electronics")
                .category("Gadgets")
                .price(new BigDecimal("297000.00"))
                .stock(10)
                .build();
    }

    @Test
    public void testCreateProduct() {
        when(productRepositories.save(any(Product.class))).thenReturn(product);
        ProductResponse productResponse = productServiceImplementation.createProduct(productRequest);
        assertNotNull(productResponse);
        assertEquals("Hp-Pavilion", productResponse.getName());
        verify(productRepositories, times(1)).save(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {
        when(productRepositories.findById(anyString())).thenReturn(Optional.of(product));
        when(productRepositories.save(any(Product.class))).thenReturn(product);
        ProductRequest updateRequest = ProductRequest.builder()
                .name("Alien-Ware")
                .description("Electronics")
                .category("Gadgets")
                .price(new BigDecimal("1970000.00"))
                .stock(4)
                .build();

        ProductResponse response = productServiceImplementation.updateProduct("productId-123", updateRequest);
        assertEquals("Alien-Ware", response.getName());
        assertEquals(new BigDecimal("1970000.00"), response.getPrice());
        verify(productRepositories, times(1)).save(any(Product.class));
    }

    @Test
    public void testFindProductById() {
        when(productRepositories.findById("productId-123")).thenReturn(Optional.of(product));
        ProductResponse productResponse = productServiceImplementation.getProductById("productId-123");
        assertNotNull(productResponse);
        assertEquals("Hp-Pavilion", productResponse.getName());
        verify(productRepositories, times(1)).findById("productId-123");
    }
}