package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Product;
import com.obswrldEcommerceApp.data.repositories.InventoryRepositories;
import com.obswrldEcommerceApp.data.repositories.ProductRepositories;
import com.obswrldEcommerceApp.dtos.Request.InventoryRequest;
import com.obswrldEcommerceApp.dtos.Response.InventoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class InventoryServiceImplementationTest {
    @Autowired
    private InventoryServiceImplementation inventoryServiceImplementation;

    @Autowired
    private InventoryRepositories inventoryRepositories;

    @Autowired
    private ProductRepositories productRepositories;

    private InventoryRequest inventoryRequest;

    @BeforeEach
    void setUp() {
        inventoryRepositories.deleteAll();
        productRepositories.deleteAll();

        Product product = Product.builder()
                .productId("productId-123")
                .name("Hp-Pavilion")
                .price(new BigDecimal("295000.00"))
                .quantity(50)
                .category("Electronics")
                .description("Gadgets")
                .build();
        productRepositories.save(product);
        inventoryRequest = InventoryRequest.builder()
                .productId("productId-123")
                .stock(50)
                .reserved(10)
                .build();
    }

    @Test
    public void testAddInventory() {
        InventoryResponse inventoryResponse = inventoryServiceImplementation.addInventory(inventoryRequest);
        assertThat(inventoryResponse).isNotNull();
        assertThat(inventoryResponse.getProductId()).isEqualTo("productId-123");
        assertThat(inventoryResponse.getStock()).isEqualTo(50);
    }

    @Test
    public void testInventoryByProductId() {
        inventoryServiceImplementation.addInventory(inventoryRequest);
        List<InventoryResponse> foundInventoryResponse = inventoryServiceImplementation.getInventoryByProductId(inventoryRequest.getProductId());
        assertThat(foundInventoryResponse).isNotNull();
        assertThat(foundInventoryResponse.size()).isEqualTo(1);
        assertThat(foundInventoryResponse.get(0).getStock()).isEqualTo(50);
    }

    @Test
    public void testAllInventories(){
        inventoryServiceImplementation.addInventory(inventoryRequest);
        InventoryRequest request = InventoryRequest.builder()
                .productId("productId-123")
                .stock(50)
                .reserved(10)
                .build();
        inventoryServiceImplementation.addInventory(request);
        List<InventoryResponse> foundInventoryResponse = inventoryServiceImplementation.getAllInventory();
        assertThat(foundInventoryResponse).isNotNull();
        assertThat(foundInventoryResponse.size()).isEqualTo(2);
    }

    @Test
    public void testUpdateInventory(){
        InventoryResponse savedResponse = inventoryServiceImplementation.addInventory(inventoryRequest);
        InventoryRequest request = InventoryRequest.builder()
                .productId(savedResponse.getProductId())
                .stock(10)
                .reserved(5)
                .build();
        InventoryResponse updateResponse = inventoryServiceImplementation.updateStock(savedResponse.getInventoryId(), request.getStock(), request.getReserved());
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getProductId()).isEqualTo(savedResponse.getProductId());
        assertThat(updateResponse.getStock()).isEqualTo(10);
        assertThat(updateResponse.getReserved()).isEqualTo(5);
    }

    @Test
    public void testFindStockLessThan(){
        inventoryServiceImplementation.addInventory(inventoryRequest);
        InventoryRequest lowStock = InventoryRequest.builder()
                .productId("productId-123")
                .stock(50)
                .reserved(10)
                .build();
        inventoryServiceImplementation.addInventory(lowStock);
        List<InventoryResponse> lowStockResponse = inventoryServiceImplementation.getLowStockItems(70);
        assertThat(lowStockResponse).isNotNull();
        assertThat(lowStockResponse.size()).isEqualTo(2);
        assertThat(lowStockResponse.get(0).getProductId()).isEqualTo("productId-123");
    }

    @Test
    public void testDeleteInventory(){
        InventoryResponse savedResponse = inventoryServiceImplementation.addInventory(inventoryRequest);
        inventoryServiceImplementation.deleteInventory(savedResponse.getInventoryId());
        assertThat(inventoryRepositories.findAll().isEmpty()).isTrue();
    }

    @Test
    public void testDeleteInventoryNotFound(){
        assertThrows(RuntimeException.class, () -> inventoryServiceImplementation.deleteInventory("invalid-Id"));
    }
}