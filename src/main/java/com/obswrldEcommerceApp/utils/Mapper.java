package com.obswrldEcommerceApp.utils;

import com.obswrldEcommerceApp.data.models.Inventory;
import com.obswrldEcommerceApp.data.models.Order;
import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.dtos.Request.ProductRequest;
import com.obswrldEcommerceApp.dtos.Response.InventoryResponse;
import com.obswrldEcommerceApp.dtos.Response.OrderResponse;
import com.obswrldEcommerceApp.dtos.Response.OrderSummaryResponse;
import com.obswrldEcommerceApp.dtos.Response.ProductResponse;

public class Mapper {

    public static ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    public static Product toEntity(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .sellerId(productRequest.getSellerId())
                .quantity(productRequest.getStock())
                .build();
    }

    public static InventoryResponse toInventoryResponse(Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        return InventoryResponse.builder()
                .inventoryId(inventory.getInventoryId())
                .productId(inventory.getProductId())
                .stock(inventory.getStock())
                .reserved(inventory.getReserved())
                .build();
    }

    public static OrderResponse toOrderResponse(Order order) {
            return OrderResponse.builder()
                    .orderId(order.getOrderId())
                    .customerId(order.getCustomerId())
                    .orderStatus(order.getStatus().name())
                    .orderItems(order.getOrderItemList())
                    .totalPrice(order.getTotalPrice())
                    .createdAt(order.getCreatedAt())
                    .build();
    }

    public static OrderSummaryResponse toOrderSummaryResponse(Order order){
        return OrderSummaryResponse.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .orderStatus(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
