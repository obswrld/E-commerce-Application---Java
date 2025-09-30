package com.obswrldEcommerceApp.dtos.Response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {

    private String cartId;
    private String customerId;
    private List<CartItemResponse> items;
}
