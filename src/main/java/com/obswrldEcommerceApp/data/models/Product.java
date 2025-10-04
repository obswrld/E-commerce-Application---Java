package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Products")
public class Product {
    @Id
    private String productId;

    @NotBlank
    private String name;

    private String description;

    @DecimalMin("0.0")
    private BigDecimal price;

    @Min(0)
    private Integer quantity;

    @NotBlank
    private String category;

    @NotBlank
    private String sellerId;

    @CreatedDate
    private Instant createdAt;
}
