package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.data.repositories.ProductRepositories;
import com.obswrldEcommerceApp.dtos.Request.ProductRequest;
import com.obswrldEcommerceApp.dtos.Response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductServiceImplementationTest {
    @Autowired
    private ProductRepositories productRepositories;

    @Autowired
    private ProductServiceImplementation productServiceImplementation;

    private Product product;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp(){
        productRepositories.deleteAll();

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
        ProductResponse productResponse = productServiceImplementation.createProduct(productRequest);
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getName()).isEqualTo("Hp-Pavilion");
        assertThat(productResponse.getPrice()).isEqualTo(new BigDecimal("297000.00"));
    }

    @Test
    public void testUpdateProduct() {
        ProductResponse productResponse = productServiceImplementation.createProduct(productRequest);
        ProductRequest request = ProductRequest.builder()
                .name("Hp-Pavilion")
                .description("Electronics")
                .category("Gadgets")
                .price(new BigDecimal("297000.00"))
                .stock(10)
                .sellerId("sellerId-123")
                .build();

        ProductResponse updated = productServiceImplementation.updateProduct(productResponse.getProductId(), request);

        assertThat(updated.getName()).isEqualTo("Hp-Pavilion");
        assertThat(updated.getPrice()).isEqualTo(new BigDecimal("297000.00"));
    }

    @Test
    public void testFindProductById() {
        ProductResponse productResponse = productServiceImplementation.createProduct(productRequest);
        ProductResponse response1 = productServiceImplementation.getProductById(productResponse.getProductId());
        assertThat(response1.getName()).isEqualTo("Hp-Pavilion");
        assertThat(response1.getCategory()).isEqualTo("Gadgets");
    }

    @Test
    public void testFindAllProducts() {
        productServiceImplementation.createProduct(productRequest);
        ProductRequest request = ProductRequest.builder()
                .name("Hp-Pavilion")
                .description("Electronics")
                .category("Gadgets")
                .price(new BigDecimal("297000.00"))
                .stock(10)
                .sellerId("sellerId-123")
                .build();
        productServiceImplementation.createProduct(productRequest);
        List<ProductResponse> products =  productServiceImplementation.getAllProducts();
        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteProductById() {
        ProductResponse savedProductResponse = productServiceImplementation.createProduct(productRequest);
        productServiceImplementation.deleteProduct(savedProductResponse.getProductId());
        assertThat(productRepositories.findById(savedProductResponse.getProductId())).isEmpty();
    }

    @Test
    public void testDeleteProductThrowExceptionWhenProductNotFound() {
        assertThrows(RuntimeException.class, () -> productServiceImplementation.deleteProduct("invalid-Id"));
    }
}