package com.example.INIT_JAVA.DTOs.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieResponseDto {
    private Long id;

    private String name;
    private List<CategoryResponseDto> categories = new ArrayList<>();
}
