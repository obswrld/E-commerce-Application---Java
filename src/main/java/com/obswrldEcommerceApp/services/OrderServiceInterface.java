package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.OrderStatus;
import com.obswrldEcommerceApp.dtos.Request.OrderRequest;
import com.obswrldEcommerceApp.dtos.Response.OrderResponse;

import java.util.List;

public interface OrderServiceInterface {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(String orderId);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getAllOrdersByCustomerId(String customerId);
    OrderResponse updateOrderByOrderStatus(String orderId, OrderStatus orderStatus);
    void deleteOrder(String orderId);
}
