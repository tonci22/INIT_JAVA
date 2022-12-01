package com.example.INIT_JAVA.services;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> getAll();

    CategoryResponseDto add(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> findByCategoryNames(List<String> names);
}
