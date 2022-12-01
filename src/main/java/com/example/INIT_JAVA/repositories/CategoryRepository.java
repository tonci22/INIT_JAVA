package com.example.INIT_JAVA.repositories;

import com.example.INIT_JAVA.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameIn(List<String> name);
}
