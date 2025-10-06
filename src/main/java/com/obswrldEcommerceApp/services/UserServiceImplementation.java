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
import java.util.Optional;

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
        Optional<User> user = userRepositories.findByEmail(userLoginRequest.getEmail());
        if(user.isEmpty()){
            return new UserLoginResponse("User not found", null);
        }
        User savedUser = user.get();
        boolean matches = bCryptPasswordEncoder.matches(userLoginRequest.getPassword(), savedUser.getPassword());
        if(!matches){
            return new UserLoginResponse("Invalid Password", savedUser.getEmail());
        }
        return new UserLoginResponse("Login Successful", savedUser.getEmail());
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
        if(!userRepositories.existsById(userId)) {
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
