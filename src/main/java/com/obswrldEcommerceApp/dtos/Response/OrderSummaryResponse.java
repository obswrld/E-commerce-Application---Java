package com.obswrldEcommerceApp.dtos.Response;
import com.obswrldEcommerceApp.data.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Builder
@AllArgsConstructor
@Data
public class OrderSummaryResponse {
    private String orderId;
    private String customerId;
    private OrderStatus orderStatus;
    private Instant createdAt;
}
