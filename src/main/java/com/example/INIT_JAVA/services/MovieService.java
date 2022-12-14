package com.example.INIT_JAVA.services;

import com.example.INIT_JAVA.DTOs.request.MovieRequestDto;
import com.example.INIT_JAVA.DTOs.response.MovieResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    List<MovieResponseDto> getAll();

    MovieResponseDto add(MovieRequestDto movieRequestDto);

    MovieResponseDto update(Long id, MovieRequestDto movieRequestDto);

    void deleteById(Long id);

    List<MovieResponseDto> findAllMoviesByName(String movieName, Pageable pageable);
}
