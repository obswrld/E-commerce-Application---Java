package com.oswrldEcommerceApp.data.repositories;


import com.obswrldEcommerceApp.Main;
import com.obswrldEcommerceApp.data.models.Inventory;
import com.obswrldEcommerceApp.data.repositories.InventoryRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
public class InventoryRepositoriesTest {

    @Autowired
    InventoryRepositories inventoryRepositories;

    private Inventory inventory1;
    private Inventory inventory2;

    @BeforeEach
    public void setup() {
        inventoryRepositories.deleteAll();

        inventory1 = Inventory.builder()
                .productId("product-123")
                .stock(5)
                .reserved(3)
                .build();

        inventory2 = Inventory.builder()
                .productId("product-333")
                .stock(10)
                .reserved(6)
                .build();

        inventoryRepositories.saveAll(List.of(inventory1, inventory2));
    }

    @Test
    public void testFindByProductId() {
        List<Inventory> inventory1Found = inventoryRepositories.findByProductId("product-123");
        assertThat(inventory1Found).hasSize(1);
        assertThat(inventory1Found.get(0).getStock()).isEqualTo(5);
    }

    @Test
    public void testFindByStock() {
        List<Inventory> inventory1Found = inventoryRepositories.findByStock(5);
        assertThat(inventory1Found).hasSize(1);
        assertThat(inventory1Found.get(0).getProductId()).isEqualTo("product-123");
    }
}
