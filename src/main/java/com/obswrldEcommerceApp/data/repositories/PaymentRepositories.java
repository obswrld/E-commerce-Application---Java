package com.obswrldEcommerceApp.data.repositories;
import com.obswrldEcommerceApp.data.models.Payment;
import com.obswrldEcommerceApp.data.models.PaymentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepositories extends MongoRepository<Payment, String> {
    List<Payment> findByOrderId(String orderId);
    List<Payment> findByCustomerId(String customerId);
    List<Payment> findByStatus(PaymentStatus status);
}
