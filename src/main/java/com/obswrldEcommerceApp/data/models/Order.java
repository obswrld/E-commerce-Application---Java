package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    @Id
    private String orderId;

    @NotBlank
    private String customerId;

    @NotBlank
    private String productId;

    @NotNull
    private List<OrderItem> orderItemList = new ArrayList<>();

    @NotNull
    private BigDecimal totalPrice;

    @Builder.Default
    private OrderStatus delivered = OrderStatus.PENDING;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updateDate;
}
