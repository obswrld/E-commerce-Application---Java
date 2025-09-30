package com.obswrldEcommerceApp.dtos.Response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class OrderResponse {
    private String orderId;
    private String customerId;
    private BigDecimal totalPrice;
    private String orderStatus;
    private Instant creationAt;
}
