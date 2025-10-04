package com.obswrldEcommerceApp.dtos.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
public class CartItemResponse {
    private String productId;
    private int quantity;
    private BigDecimal price;
}
