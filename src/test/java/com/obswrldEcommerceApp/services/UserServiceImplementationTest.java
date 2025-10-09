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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceImplementationTest {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserServiceImplementation userService;

    private UserRegistrationRequest registrationRequest;

    @BeforeEach
    public void setUp() {
        userRepositories.deleteAll();

        registrationRequest = UserRegistrationRequest.builder()
                .name("John Mike")
                .password("567tr")
                .email("johnmike1@gmail.com")
                .roles(Set.of(Role.BUYER))
                .build();
    }

    @Test
    public void testRegisterUser() {
        UserResponse userResponse = userService.register(registrationRequest);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getEmail()).isEqualTo("johnmike1@gmail.com");
        User saved = userRepositories.findByEmail("johnmike1@gmail.com").orElseThrow();
        assertThat(bCryptPasswordEncoder.matches("567tr", saved.getPassword())).isTrue();
    }

    @Test
    public void testLoinUserSuccessful(){
        userService.register(registrationRequest);
        UserLoginRequest userLoginRequest = new UserLoginRequest("johnmike1@gmail.com", "567tr");
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
        assertThat(userLoginResponse).isNotNull();
        assertThat(userLoginResponse.getMessage()).isEqualTo("Login Successful");
        assertThat(userLoginResponse.getEmail()).isEqualTo("johnmike1@gmail.com");
    }

    @Test
    public void testLoginUserFailed(){
        userService.register(registrationRequest);
        UserLoginRequest userLoginRequest = new UserLoginRequest("johnmike1@gmail.com", "wrongPassword");
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
        assertThat(userLoginResponse.getMessage()).isEqualTo("Invalid Password");
    }

    @Test
    public void testFindUserByEmail(){
        userService.register(registrationRequest);
        UserResponse userResponse = userService.findByEmail("johnmike1@gmail.com");
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo("John Mike");
    }

    @Test
    public void testDeleteUserById(){
        UserResponse userResponse = userService.register(registrationRequest);
        userService.deleteUser(userResponse.getUserId());
        assertThat(userRepositories.findById(userResponse.getUserId()).isEmpty()).isTrue();
    }

    @Test
    public void testGetAllUsers(){
        userService.register(registrationRequest);
        UserRegistrationRequest secondUser = UserRegistrationRequest.builder()
                .name("James Smith")
                .email("jamessmith1@gmail.com")
                .password("12345")
                .roles(Set.of(Role.BUYER))
                .build();
        userService.register(secondUser);
        List<UserResponse> allUsers = userService.getAllUsers();
        assertThat(allUsers).hasSize(2);
        assertThat(allUsers.get(0).getEmail()).isEqualTo("johnmike1@gmail.com");
        assertThat(allUsers.get(1).getEmail()).isEqualTo("jamessmith1@gmail.com");
    }

    @Test
    public void testDeleteUserThatDoesNotExist(){
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser("non-existing-user");
        });
        assertThat(exception.getMessage()).isEqualTo("User not found");

    }
}