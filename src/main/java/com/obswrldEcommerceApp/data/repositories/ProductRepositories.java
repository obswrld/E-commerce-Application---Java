package com.obswrldEcommerceApp.data.repositories;
import com.obswrldEcommerceApp.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepositories extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
    List<Product> findBySellerId(String sellerId);
    List<Product> findByNameContainingIgnoreCase(String nameKeyword);
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
