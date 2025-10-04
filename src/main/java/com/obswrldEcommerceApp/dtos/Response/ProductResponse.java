package com.obswrldEcommerceApp.dtos.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private String productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String description;
}
