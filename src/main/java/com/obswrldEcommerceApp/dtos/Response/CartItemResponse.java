package com.obswrldEcommerceApp.dtos.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private String productId;
    private int quantity;
    private BigDecimal price;
}
