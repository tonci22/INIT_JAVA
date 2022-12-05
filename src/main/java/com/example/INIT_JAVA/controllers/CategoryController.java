package com.example.INIT_JAVA.controllers;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;
import com.example.INIT_JAVA.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<Set<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.add(categoryRequestDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("id") Long id,
                                                              @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.update(id, categoryRequestDto));
    }
}
