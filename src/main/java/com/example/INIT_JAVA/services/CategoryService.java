package com.example.INIT_JAVA.services;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;

import java.util.Set;

public interface CategoryService {

    Set<CategoryResponseDto> getAll();

    CategoryResponseDto add(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto);

    Set<CategoryResponseDto> findByCategoryNames(Set<String> names);
}
