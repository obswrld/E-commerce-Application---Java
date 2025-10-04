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

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserServiceInterface{
    private final UserRepositories userRepositories;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponse register(UserRegistrationRequest userRegistrationRequest) {
        String password = bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword());
        User user = User.builder()
                .name(userRegistrationRequest.getName())
                .email(userRegistrationRequest.getEmail())
                .password(password)
                .roles(userRegistrationRequest.getRoles())
                .address(userRegistrationRequest.getAddress())
                .build();

        User savedUser = userRepositories.save(user);
        return new UserResponse(savedUser.getUserId(), savedUser.getName(), savedUser.getEmail(),
                savedUser.getRoles(), savedUser.getAddress());
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return userRepositories.findByEmail(userLoginRequest.getEmail())
                .filter(user -> bCryptPasswordEncoder.matches(userLoginRequest.getPassword(), user.getPassword()))
                .map(user -> new UserLoginResponse("Login Successful", user.getUserId(), user.getRoles(),
                        user.getEmail()))
                .orElse(new UserLoginResponse("Invalid Password", null, null, null));
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepositories.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getUserId(), user.getName(), user.getEmail(), user.getRoles(), user.getAddress());
    }

    @Override
    public void deleteUser(String userId){
        userRepositories.deleteById(userId);
    }
}
