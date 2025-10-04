package com.obswrldEcommerceApp.data.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String street;
    private String city;
    private String country;
    private String zipcode;
}
