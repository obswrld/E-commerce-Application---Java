package com.obswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.data.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepositories extends MongoRepository<Review, String> {

    List<Review> findByProductId(String productId);

    List<Review> findByCustomerId(String customerId);

    List<Review> findByProductIdAndCustomerId(String productId, String customerId);
}
