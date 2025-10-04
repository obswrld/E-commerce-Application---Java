package com.obswrldEcommerceApp.dtos.Response;
import com.obswrldEcommerceApp.data.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class OrderResponse {
    private String orderId;
    private String customerId;
    private List<OrderItem> orderItems;
    private BigDecimal totalPrice;
    private String orderStatus;
    private Instant createdAt;
}
