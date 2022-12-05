package com.example.INIT_JAVA.DTOs.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MovieResponseDto {
    private Long id;

    private String name;
    private Set<CategoryResponseDto> categories = new HashSet<>();
}
