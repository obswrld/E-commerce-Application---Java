package com.obswrldEcommerceApp.dtos.Response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class ProductResponse {
    private String productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String description;
    private Instant createdAt;
    private String sellerId;
}
