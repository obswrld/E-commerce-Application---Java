package com.obswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.data.models.Cart;
import com.obswrldEcommerceApp.data.models.CartItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class CartRepositoriesTest {

    @Autowired
    CartRepositories cartRepositories;

    Cart cart;

    @BeforeEach
    public void setup1() {
        cartRepositories.deleteAll();

        CartItems item1 = CartItems.builder()
                .productId("product-123")
                .quantity(2)
                .price(new BigDecimal("25000.00"))
                .build();

        CartItems item2 = CartItems.builder()
                .productId("product-343")
                .quantity(3)
                .price(new BigDecimal("75000.00"))
                .build();

        cart = Cart.builder()
                .customerId("customer-123")
                .items(List.of(item1, item2))
                .build();

        cartRepositories.save(cart);
    }

    @Test
    public void testFindByCustomerId(){
        Optional<Cart> cartOptional = cartRepositories.findByCustomerId("customer-123");
        assertThat(cartOptional).isPresent();
        assertThat(cartOptional.get().getItems()).hasSize(2);
        assertThat(cartOptional.get().getCustomerId()).isEqualTo("customer-123");
    }

    @Test
    public void testCustomerNotFound(){
        Optional<Cart> cartOptional = cartRepositories.findByCustomerId("Non-existing");
        assertThat(cartOptional).isNotPresent();
    }
}
