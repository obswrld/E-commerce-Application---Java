package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotNull
    private String name;
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotBlank
    private String category;

    @NotBlank
    private String sellerId;

    @CreatedDate
    private Instant createdAt;
}
