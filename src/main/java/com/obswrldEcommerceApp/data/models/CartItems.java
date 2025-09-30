package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartItems {
    @Id
    private String productId;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    @Min(1)
    private BigDecimal price;
}
