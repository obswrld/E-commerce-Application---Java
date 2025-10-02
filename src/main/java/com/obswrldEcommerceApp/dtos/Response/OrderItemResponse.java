package com.obswrldEcommerceApp.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemResponse {
    private String productId;
    private int quantity;
    private BigDecimal price;
}
