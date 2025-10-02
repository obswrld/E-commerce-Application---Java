package com.obswrldEcommerceApp.dtos.Request;

import com.obswrldEcommerceApp.data.models.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {
    private String customerId;
    private List<CartItemRequest> items;
    private Address address;
    private String paymentMethod;
}
