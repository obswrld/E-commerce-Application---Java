package com.obswrldEcommerceApp.dtos.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {

    private String cartId;
    private String customerId;
    private List<CartItemResponse> items;
}
