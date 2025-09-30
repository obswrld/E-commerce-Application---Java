package com.obswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.data.models.Inventory;
import org.apache.el.stream.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepositories extends MongoRepository<Inventory, String> {

    List<Inventory> findByProductId(String productId);

    List<Inventory> findByStock(int stockThreshold);

}
