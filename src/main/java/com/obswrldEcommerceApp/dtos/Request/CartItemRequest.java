package com.obswrldEcommerceApp.dtos.Request;

import lombok.Data;

@Data
public class CartItemRequest {
    private String productId;
    private int stock;
}
