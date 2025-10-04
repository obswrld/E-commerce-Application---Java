package com.obswrldEcommerceApp.services;
import com.obswrldEcommerceApp.dtos.Request.UserLoginRequest;
import com.obswrldEcommerceApp.dtos.Request.UserRegistrationRequest;
import com.obswrldEcommerceApp.dtos.Response.UserLoginResponse;
import com.obswrldEcommerceApp.dtos.Response.UserResponse;

public interface UserServiceInterface {
    UserResponse register(UserRegistrationRequest userRegistrationRequest);
    UserLoginResponse login(UserLoginRequest userLoginRequest);
    UserResponse findByEmail(String email);
    void deleteUser(String userId);
}
