package com.obswrldEcommerceApp.dtos.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @Data
    @Builder
    public static class PaymentRequest {
        private String orderId;
        private String customerId;
        private BigDecimal amount;
        private String merchant;
    }
}
