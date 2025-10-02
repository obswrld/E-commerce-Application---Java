package com.obswrldEcommerceApp.dtos.Response;

import com.obswrldEcommerceApp.data.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderSummaryResponse {
    private String orderId;
    private String customerId;
    private OrderStatus orderStatus;
    private Instant createdAt;
}
