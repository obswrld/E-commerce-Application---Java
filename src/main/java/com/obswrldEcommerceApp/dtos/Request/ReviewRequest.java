package com.obswrldEcommerceApp.dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewRequest {
    @NotBlank
    private String productId;

    @NotNull
    private int rating;
    private String comment;

    @NotBlank
    private String customerId;
}
