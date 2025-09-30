package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Reviews")
public class Review {
    @Id
    private String reviewId;

    @NotBlank
    private String productId;

    @NotBlank
    private String customerId;

    @Min(1)
    @Max(5)
    private int rating;

    private String comment;

    private Instant createdAt =  Instant.now();
}
