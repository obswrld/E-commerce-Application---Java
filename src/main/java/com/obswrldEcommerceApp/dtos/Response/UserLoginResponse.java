package com.obswrldEcommerceApp.dtos.Response;

import com.obswrldEcommerceApp.data.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Builder
@AllArgsConstructor
@Data
public class UserLoginResponse {
    private String message;
//    private String userId;
    @Email
    @NotBlank
    private String email;
}
