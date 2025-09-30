package com.obswrldEcommerceApp.dtos.Response;

import com.obswrldEcommerceApp.data.models.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentResponse {
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private OrderStatus orderStatus;
}

