package com.obswrldEcommerceApp.data.repositories;
import com.obswrldEcommerceApp.data.models.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryRepositories extends MongoRepository<Inventory, String> {
    List<Inventory> findByProductId(String productId);
    List<Inventory> findByStockLessThan(int stockThreshold);
}
