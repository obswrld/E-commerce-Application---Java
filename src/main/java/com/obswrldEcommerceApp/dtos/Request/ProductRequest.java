package com.obswrldEcommerceApp.dtos.Request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String category;
    private String sellerId;
}
