package com.obswrldEcommerceApp.services;

import com.obswrldEcommerceApp.data.models.Role;
import com.obswrldEcommerceApp.data.models.User;
import com.obswrldEcommerceApp.data.repositories.UserRepositories;
import com.obswrldEcommerceApp.dtos.Request.UserLoginRequest;
import com.obswrldEcommerceApp.dtos.Request.UserRegistrationRequest;
import com.obswrldEcommerceApp.dtos.Response.UserLoginResponse;
import com.obswrldEcommerceApp.dtos.Response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplementationTest {

    @Mock
    private UserRepositories userRepositories;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImplementation userService;

    private UserRegistrationRequest registrationRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        registrationRequest = UserRegistrationRequest.builder()
                .name("John Mike")
                .password("567tr")
                .email("johnmike1@gmail.com")
                .roles(Set.of(Role.BUYER))
                .build();
    }

    @Test
    public void testRegisterUser() {
        when(bCryptPasswordEncoder.encode("567tr")).thenReturn("encoded567tr");

        User saveUser = User.builder()
                .userId("1")
                .name("John Mike")
                .password("567tr")
                .email("johnmike1@gmail.com")
                .roles(Set.of(Role.BUYER))
                .build();
        when(userRepositories.save(any(User.class))).thenReturn(saveUser);

        UserResponse userResponse = userService.register(registrationRequest);

        assertThat(userResponse.getUserId()).isEqualTo("1");
        assertThat(userResponse.getEmail()).isEqualTo("johnmike1@gmail.com");
    }

    @Test
    public void testLoinUserSuccessful(){
        User user = User.builder()
                .email("johnmike1@gmail.com")
                .password("encoded567tr")
                .build();

        when(userRepositories.findByEmail("johnmike1@gmail.com"))
                .thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches("567tr", "encoded567tr")).thenReturn(true);
        UserLoginRequest userLoginRequest = new UserLoginRequest("johnmike1@gmail.com","567tr");
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);

        assertThat(userLoginResponse.getMessage()).isEqualTo("Login Successful");
    }

    @Test
    public void testLoginUserFailed(){
        User user = User.builder()
                .email("johnmike1@gmail.com")
                .password("encoded567tr")
                .build();

        when(userRepositories.findByEmail("johnmike1@gmail.com"))
                .thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches("wrong", "encoded567tr")).thenReturn(false);
        UserLoginRequest userLoginRequest = new UserLoginRequest("johnmike1@gmail.com","wrong");
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);

        assertThat(userLoginResponse.getMessage()).isEqualTo("Invalid Password");
    }

}