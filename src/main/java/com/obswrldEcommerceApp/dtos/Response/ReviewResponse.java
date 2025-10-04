package com.obswrldEcommerceApp.dtos.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@AllArgsConstructor
@Builder
@Data
public class ReviewResponse {
    private String reviewId;
    private String productId;
    private int rating;
    private String comment;
    private String customerId;
    private Instant createdAt;
}
