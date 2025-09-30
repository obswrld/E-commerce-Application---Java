package com.obswrldEcommerceApp.data.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Inventory")
public class Inventory {

    @Id
    private String inventoryId;

    @NotBlank
    private String productId;

    private int stock;

    private int reserved;
}
