package com.obswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.data.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
public class ProductRepositoriesTest {

    @Autowired
    private ProductRepositories productRepositories;

    private Product phone;
    private Product laptop;

    @BeforeEach
    public void setup(){
        productRepositories.deleteAll();

        laptop = Product.builder()
                .name("Hp Pavilion 2018")
                .description("Hp-class")
                .category("Gadgets")
                .price(new BigDecimal("125000.00"))
                .sellerId("sellerId-232")
                .quantity(10)
                .build();

        phone = Product.builder()
                .name("iPhone 15pro")
                .description("Apple Smart Phones")
                .category("Gadgets")
                .price(new BigDecimal("1250000.00"))
                .sellerId("sellerId-123")
                .quantity(5)
                .build();

        productRepositories.save(phone);
        productRepositories.save(laptop);
    }

    @Test
    public void testByCategory(){
        List<Product> products = productRepositories.findByCategory("Gadgets");
        assertThat(products).hasSize(2);
    }
    
    @Test
    public void testFindByPriceBetween(){
        List<Product> products = productRepositories.findByPriceBetween(
                new BigDecimal("125000.00"), new BigDecimal("12550000.00")
        );
        assertThat(products).hasSize(1);
    }

    @Test
    public void testFindBySellerId(){
        List<Product> seller = productRepositories.findBySellerId("sellerId-123");
        assertThat(seller).hasSize(1);
    }

    @Test
    public void testFindByIgnoreCase(){
        List<Product> products = productRepositories.findByNameContainingIgnoreCase("iphone");
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("iPhone 15pro");
    }
}
