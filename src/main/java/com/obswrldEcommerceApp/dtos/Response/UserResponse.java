package com.obswrldEcommerceApp.dtos.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String userId;
    private String name;
    private String email;
}
