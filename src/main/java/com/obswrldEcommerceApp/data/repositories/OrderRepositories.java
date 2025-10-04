package com.obswrldEcommerceApp.data.repositories;
import com.obswrldEcommerceApp.data.models.Order;
import com.obswrldEcommerceApp.data.models.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepositories extends MongoRepository<Order, String> {
    List<Order> findByCustomerId(String customerId);
    List<Order> findByStatus(OrderStatus status);
}
