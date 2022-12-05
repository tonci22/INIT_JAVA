package com.example.INIT_JAVA;

import com.example.INIT_JAVA.domain.Category;
import com.example.INIT_JAVA.repositories.CategoryRepository;
import com.example.INIT_JAVA.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EntityTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void checkIfDatabaseSavesUser() {

        assertEquals("admin", userRepository.findByName("admin").getName());

        assertEquals("user", userRepository.findByName("user").getName());
    }

    @Test
    public void checkIfNoDuplicatesCanBeSavedInDatabase() {
        Category category = new Category();
        category.setName("Comedy");

        Exception exception = Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> categoryRepository.save(category));

        String expectedResult = "could not execute statement";

        assertTrue(exception.getLocalizedMessage().contains(expectedResult));
    }
}