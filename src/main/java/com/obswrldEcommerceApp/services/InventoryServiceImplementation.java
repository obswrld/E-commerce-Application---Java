package com.obswrldEcommerceApp.services;


import com.obswrldEcommerceApp.data.models.Inventory;
import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.data.repositories.InventoryRepositories;
import com.obswrldEcommerceApp.data.repositories.ProductRepositories;
import com.obswrldEcommerceApp.dtos.Request.InventoryRequest;
import com.obswrldEcommerceApp.dtos.Response.InventoryResponse;
import com.obswrldEcommerceApp.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImplementation implements InventoryServiceInterface{

    private final InventoryRepositories inventoryRepositories;
    private final ProductRepositories productRepositories;

    @Override
    public InventoryResponse addInventory(InventoryRequest inventoryRequest){
        Product product = productRepositories.findById(inventoryRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Inventory inventory = Inventory.builder()
                .productId(inventoryRequest.getProductId())
                .stock(inventoryRequest.getStock())
                .reserved(inventoryRequest.getReserved())
                .build();
        Inventory savedInventory = inventoryRepositories.save(inventory);
        return Mapper.toInventoryResponse(savedInventory);
    }

    @Override
    public List<InventoryResponse> getInventoryByProductId(String productId){
        List<Inventory> inventory = inventoryRepositories.findByProductId(productId);
        return inventory.stream()
                .map(Mapper::toInventoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryResponse> getAllInventory(){
        List<Inventory> inventory = inventoryRepositories.findAll();
        return inventory.stream()
                .map(Mapper::toInventoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponse updateStock(String inventoryId, Integer quantity, Integer reserved){
        Inventory inventory =  inventoryRepositories.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setStock(quantity);
        inventory.setReserved(reserved);
        Inventory updatedInventory = inventoryRepositories.save(inventory);
        return Mapper.toInventoryResponse(updatedInventory);
    }

    @Override
    public List<InventoryResponse> getLowStockItems(int threshold){
        return inventoryRepositories.findByStockLessThan(threshold)
                .stream()
                .map(Mapper::toInventoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteInventory(String inventoryId){
        if(!inventoryRepositories.existsById(inventoryId)){
            throw new RuntimeException("Inventory not found");
        }
        inventoryRepositories.deleteById(inventoryId);
    }
}
