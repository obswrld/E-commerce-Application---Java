package com.obswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.data.models.Role;
import com.obswrldEcommerceApp.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
public class UserRepositoriesTest {

    @Autowired
    private UserRepositories userRepository;

    private User testUser;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();

        testUser = User.builder()
                .name("Oba")
                .email("republicoba1@gmail.com")
                .password("1234")
                .roles(Set.of(Role.BUYER))
                .build();
    }

    @Test
    public void testSaveUser(){
        User savedUser = userRepository.save(testUser);
        assertThat(savedUser.getUserId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("republicoba1@gmail.com");
    }

    @Test
    public void testFindByEmail(){
        User savedUser = userRepository.save(testUser);
        Optional<User> foundUser = userRepository.findByEmail("republicoba1@gmail.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("republicoba1@gmail.com");
    }

    @Test
    public void testThatEmailExist(){
        User savedUser = userRepository.save(testUser);

        boolean exists = userRepository.existsByEmail("republicoba1@gmail.com");
        assertThat(exists).isTrue();
    }

    @Test
    public void testForDuplicateEmail(){
        User user1 = User.builder()
                .name("Kim")
                .email("graceMike21@gmail.com")
                .password("2234")
                .build();

        User user2 = User.builder()
                .name("Sam")
                .email("graceMike21@gmail.com")
                .password("22323")
                .build();

         userRepository.save(user1);
         assertThrows(org.springframework.dao.DuplicateKeyException.class, ()->{
             userRepository.save(user2);
         });
    }

    @Test
    public void testFindByRoles(){
        User buyer = User.builder()
                .name("Esther")
                .email("estherJohn50@yahoo.com")
                .password("1234we")
                .roles(Set.of(Role.BUYER))
                .build();

        User seller = User.builder()
                .name("Stella")
                .email("stellaAdams@gmail.com")
                .password("12332324we")
                .roles(Set.of(Role.SELLER))
                .build();

        userRepository.save(buyer);
        userRepository.save(seller);

        var customers = userRepository.findByRoles(Set.of(Role.BUYER));
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getEmail()).isEqualTo("estherJohn50@yahoo.com");
    }
}
