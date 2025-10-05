package com.obswrldEcommerceApp.services;
import com.obswrldEcommerceApp.data.models.User;
import com.obswrldEcommerceApp.data.repositories.UserRepositories;
import com.obswrldEcommerceApp.dtos.Request.UserLoginRequest;
import com.obswrldEcommerceApp.dtos.Request.UserRegistrationRequest;
import com.obswrldEcommerceApp.dtos.Response.UserLoginResponse;
import com.obswrldEcommerceApp.dtos.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserServiceInterface{
    private final UserRepositories userRepositories;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponse register(UserRegistrationRequest userRegistrationRequest) {
        if (userRepositories.existsByEmail(userRegistrationRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        String password = bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword());
        User user = User.builder()
                .name(userRegistrationRequest.getName())
                .email(userRegistrationRequest.getEmail())
                .password(password)
                .roles(userRegistrationRequest.getRoles())
                .address(userRegistrationRequest.getAddress())
                .build();
        User savedUser = userRepositories.save(user);
        return new UserResponse(
                savedUser.getUserId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRoles(),
                savedUser.getAddress());
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepositories.findByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!bCryptPasswordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return new UserLoginResponse(
                "Login Succesful",
                user.getUserId(),
                user.getRoles(),
                user.getEmail()
        );
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepositories.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRoles(),
                user.getAddress()
        );
    }

    @Override
    public void deleteUser(String userId){
        if(!userRepositories.existsByEmail(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepositories.deleteById(userId);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepositories.findAll().stream()
                .map(user -> new UserResponse(
                        user.getUserId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRoles(),
                        user.getAddress()
                ))
                .toList();
    }
}
