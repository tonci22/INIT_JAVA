package com.example.INIT_JAVA.mappers;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;
import com.example.INIT_JAVA.domain.Category;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Set<CategoryResponseDto> mapToDto(Set<Category> categories);

    Category mapToDto(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto mapToDto(Category category);

    Set<Category> mapToDtoList(Set<CategoryResponseDto> categories);

}
