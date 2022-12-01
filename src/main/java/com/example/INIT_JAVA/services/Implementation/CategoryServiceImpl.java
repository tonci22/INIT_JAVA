package com.example.INIT_JAVA.services.Implementation;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;
import com.example.INIT_JAVA.domain.Category;
import com.example.INIT_JAVA.exceptions.DuplicateEntityException;
import com.example.INIT_JAVA.exceptions.RepositoryNotFoundException;
import com.example.INIT_JAVA.mappers.CategoryMapper;
import com.example.INIT_JAVA.repositories.CategoryRepository;
import com.example.INIT_JAVA.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(final CategoryRepository categoryRepository, final CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryMapper.mapToDto(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDto add(CategoryRequestDto categoryRequestDto) {

        Category category = categoryMapper.mapToDto(categoryRequestDto);
        categoryRepository.save(category);

        return categoryMapper.mapToDto(category);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        categoryRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Category ID not found"));

        if (categoryRepository.findByNameIn(List.of(categoryRequestDto.getName())).size() != 0)
            throw new DuplicateEntityException("Category with this name already exists");

        Category category = categoryMapper.mapToDto(categoryRequestDto);
        category.setId(id);

        categoryRepository.save(category);
        return categoryMapper.mapToDto(category);
    }

    @Override
    public List<CategoryResponseDto> findByCategoryNames(List<String> names) {
        return categoryMapper.mapToDto(categoryRepository.findByNameIn(names));
    }
}
