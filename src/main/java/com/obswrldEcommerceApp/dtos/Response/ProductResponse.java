package com.obswrldEcommerceApp.dtos.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String description;
    private String sellerId;
}
