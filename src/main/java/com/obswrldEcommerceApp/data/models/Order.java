package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "Orders")
public class Order {
    @Id
    private String orderId;

    @NotBlank
    private String customerId;

    @NotNull
    private List<OrderItem> orderItemList = new ArrayList<>();

    @NotNull
    private BigDecimal totalPrice;

    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updateDate;
}
