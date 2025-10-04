package com.obswrldEcommerceApp.dtos.Request;

import com.obswrldEcommerceApp.data.models.Address;
import com.obswrldEcommerceApp.data.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private Set<Role> roles;

    @NotBlank
    private String password;

    private Address address;
}
