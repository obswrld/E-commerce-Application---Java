package com.obswrldEcommerceApp.data.repositories;
import com.obswrldEcommerceApp.data.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepositories extends MongoRepository<Cart, String> {
    Optional<Cart> findByCustomerId(String customerId);
    boolean existsByCustomerId(String customerId);
}
