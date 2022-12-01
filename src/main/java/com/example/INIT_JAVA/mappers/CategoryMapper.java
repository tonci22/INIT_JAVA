package com.example.INIT_JAVA.mappers;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;
import com.example.INIT_JAVA.domain.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryResponseDto> mapToDto(List<Category> categories);

    Category mapToDto(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto mapToDto(Category category);

    List<Category> mapToDtoList(List<CategoryResponseDto> categories);

}
