package com.obswrldEcommerceApp.dtos.Response;

import com.obswrldEcommerceApp.data.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentResponse {
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private OrderStatus orderStatus;
    private Instant createdAt;
}

