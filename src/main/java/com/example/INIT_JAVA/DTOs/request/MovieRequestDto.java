package com.example.INIT_JAVA.DTOs.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieRequestDto {

    private String name;

    private List<CategoryRequestDto> categories = new ArrayList<>();
}
