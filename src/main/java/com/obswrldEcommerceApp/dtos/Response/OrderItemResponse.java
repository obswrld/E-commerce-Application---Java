package com.obswrldEcommerceApp.dtos.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Data
public class OrderItemResponse {
    private String productId;
    private int quantity;
    private BigDecimal price;
}
