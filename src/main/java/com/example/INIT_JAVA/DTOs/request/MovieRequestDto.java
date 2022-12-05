package com.example.INIT_JAVA.DTOs.request;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class MovieRequestDto {

    String name;
    List<CategoryRequestDto> categories = new ArrayList<>();
}
