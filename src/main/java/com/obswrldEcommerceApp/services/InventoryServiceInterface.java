package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.dtos.Request.InventoryRequest;
import com.obswrldEcommerceApp.dtos.Response.InventoryResponse;

import java.util.List;

public interface InventoryServiceInterface {
    InventoryResponse addInventory(InventoryRequest inventoryRequest);
    List<InventoryResponse> getInventoryByProductId(String productId);
    List<InventoryResponse> getAllInventory();
    InventoryResponse updateStock(String inventoryId, Integer quantity, Integer reserved);
    List<InventoryResponse> getLowStockItems(int threshold);
    void deleteInventory(String inventoryId);
}
