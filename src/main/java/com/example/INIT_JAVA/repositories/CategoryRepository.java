package com.example.INIT_JAVA.repositories;

import com.example.INIT_JAVA.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findByNameIn(Set<String> name);
}
