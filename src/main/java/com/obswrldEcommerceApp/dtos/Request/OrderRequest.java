package com.obswrldEcommerceApp.dtos.Request;

import com.obswrldEcommerceApp.data.models.Address;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String customerId;
    private List<CartItemRequest> items;
    private Address address;
    private String paymentMethod;
}
