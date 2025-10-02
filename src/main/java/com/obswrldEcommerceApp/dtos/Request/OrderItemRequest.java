package com.obswrldEcommerceApp.dtos.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemRequest {
    @NotNull
    private String productId;

    @NotNull
    private int quantity;
}
