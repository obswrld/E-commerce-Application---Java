package com.obswrldEcommerceApp.data.models;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
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

    public int getAvailableStock(){
        return stock - reserved;
    }
}
