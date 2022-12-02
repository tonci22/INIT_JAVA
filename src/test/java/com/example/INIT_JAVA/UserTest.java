package com.example.INIT_JAVA;

import com.example.INIT_JAVA.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkIfDatabaseSavesUser() {

        Assertions.assertEquals("admin", userRepository.findByName("admin").getName());

        Assertions.assertEquals("user", userRepository.findByName("user").getName());
    }
}