package com.obswrldEcommerceApp.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItemResponse {
    private String productId;
    private int quantity;
    private BigDecimal price;
}
