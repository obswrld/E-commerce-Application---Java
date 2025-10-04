package com.obswrldEcommerceApp.dtos.Response;
import com.obswrldEcommerceApp.data.models.OrderStatus;
import com.obswrldEcommerceApp.data.models.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@Data
@Builder
public class PaymentResponse {
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private PaymentStatus status;
    private Instant createdAt;
}

