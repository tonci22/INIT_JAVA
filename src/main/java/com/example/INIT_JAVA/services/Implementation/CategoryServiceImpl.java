package com.example.INIT_JAVA.services.Implementation;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;
import com.example.INIT_JAVA.domain.Category;
import com.example.INIT_JAVA.exceptions.EntityNotFoundException;
import com.example.INIT_JAVA.mappers.CategoryMapper;
import com.example.INIT_JAVA.repositories.CategoryRepository;
import com.example.INIT_JAVA.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Set<CategoryResponseDto> getAll() {
        return categoryMapper.mapToDto(new HashSet<>(categoryRepository.findAll()));
    }

    @Override
    public CategoryResponseDto add(CategoryRequestDto categoryRequestDto) {

        Category category = categoryMapper.mapToDto(categoryRequestDto);
        categoryRepository.save(category);

        return categoryMapper.mapToDto(category);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category ID not found"));

        Category category = categoryMapper.mapToDto(categoryRequestDto);
        category.setId(id);

        categoryRepository.save(category);
        return categoryMapper.mapToDto(category);
    }

    @Override
    public Set<CategoryResponseDto> findByCategoryNames(Set<String> names) {
        return categoryMapper.mapToDto(categoryRepository.findByNameIn(names));
    }
}
