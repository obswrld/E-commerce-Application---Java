package com.obswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.data.models.Order;
import com.obswrldEcommerceApp.data.models.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class OrderRepositoriesTest {

    @Autowired
    private OrderRepositories orderRepositories;

    private Order order1;
    private Order order2;

    @BeforeEach
    public void setup(){
        orderRepositories.deleteAll();

        order1 = Order.builder()
                .customerId("customer-123")
                .totalPrice(new BigDecimal("125000.00"))
                .status(OrderStatus.PENDING)
                .build();

        order2 = Order.builder()
                .customerId("customer-009")
                .totalPrice(new BigDecimal("235000.00"))
                .status(OrderStatus.DELIVERED)
                .build();

        orderRepositories.save(order1);
        orderRepositories.save(order2);
    }

    @Test
    public void findByCustomerId(){
        List<Order> orders = orderRepositories.findByCustomerId("customer-123");
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getCustomerId()).isEqualTo("customer-123");
    }

    @Test
    public void findByStatus(){
        List<Order> orders = orderRepositories.findByStatus(OrderStatus.PENDING);
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getStatus()).isEqualTo(OrderStatus.PENDING);
    }

    @Test
    public void testForMultipleOrderBySameCustomerId(){
        Order secondOrder = Order.builder()
                .customerId("customer-123")
                .totalPrice(new BigDecimal("300000.00"))
                .status(OrderStatus.SHIPPED)
                .build();

        orderRepositories.save(secondOrder);

        List<Order> orders = orderRepositories.findByCustomerId("customer-123");
        assertThat(orders).hasSize(2);
        assertThat(orders).extracting(Order::getStatus).contains(OrderStatus.SHIPPED,  OrderStatus.PENDING);
    }
}
