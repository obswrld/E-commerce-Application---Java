package com.obswrldEcommerceApp.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartResponse {

    private String cartId;
    private String customerId;
    private List<CartItemResponse> items;
    private BigDecimal total;
}
