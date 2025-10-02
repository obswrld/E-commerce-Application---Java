package com.obswrldEcommerceApp.dtos.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryRequest {

    @NotBlank
    private String productId;

    @NotNull
    private int stock;

    @NotNull
    private int reserved;
}
