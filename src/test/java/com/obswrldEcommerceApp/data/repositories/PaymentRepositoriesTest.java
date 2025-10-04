package com.obswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.data.models.Payment;
import com.obswrldEcommerceApp.data.models.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class PaymentRepositoriesTest {
    @Autowired
    private PaymentRepositories paymentRepositories;

    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    public void setUp() {
        paymentRepositories.deleteAll();
        payment1 = Payment.builder()
                .customerId("Customer-123")
                .orderId("Order-123")
                .amount(new BigDecimal("350000.00"))
                .status(PaymentStatus.PENDING)
                .method("Card")
                .build();

        payment2 = Payment.builder()
                .customerId("Customer-333")
                .orderId("Order-333")
                .amount(new BigDecimal("450000.00"))
                .status(PaymentStatus.SUCCESS)
                .method("Card")
                .build();

        paymentRepositories.saveAll(List.of(payment1, payment2));
    }

    @Test
    public void testFindByCustomerId() {
        List<Payment> paymentFound1 = paymentRepositories.findByCustomerId("Customer-123");
        assertThat(paymentFound1).hasSize(1);
        assertThat(paymentFound1.get(0).getAmount()).isEqualTo(new BigDecimal("350000.00"));
    }

    @Test
    public void testFindByOrderId() {
        List<Payment> paymentFound1 = paymentRepositories.findByOrderId("Order-123");
        assertThat(paymentFound1).hasSize(1);
        assertThat(paymentFound1.get(0).getStatus()).isEqualTo(PaymentStatus.PENDING);
    }

    @Test
    public void testFindByPaymentStatus() {
        List<Payment> paymentFound1 = paymentRepositories.findByStatus(PaymentStatus.SUCCESS);
        assertThat(paymentFound1).hasSize(1);
        assertThat(paymentFound1.get(0).getMethod()).isEqualTo("Card");
    }
}