package com.obswrldEcommerceApp.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryResponse {
    private String inventoryId;
    private String productId;
    private int stock;
    private int reserved;
}
