package com.obswrldEcommerceApp.dtos.Request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String orderId;
    private String customerId;
    private BigDecimal price;
    private String method;
}
