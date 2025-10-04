package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "Payments")
public class Payment {
    @Id
    private String paymentId;

    @NotBlank
    private String orderId;

    @NotBlank
    private String customerId;

    @DecimalMin("0.0")
    private BigDecimal amount;

    private String method;

    private PaymentStatus status;

    private Instant createdAt = Instant.now();
}
