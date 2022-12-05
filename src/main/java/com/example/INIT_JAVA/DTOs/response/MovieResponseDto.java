package com.example.INIT_JAVA.DTOs.response;

import lombok.Value;

import java.util.HashSet;
import java.util.Set;

@Value
public class MovieResponseDto {

    Long id;
    String name;
    Set<CategoryResponseDto> categories = new HashSet<>();
}
