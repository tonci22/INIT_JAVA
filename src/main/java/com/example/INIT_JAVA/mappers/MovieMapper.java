package com.example.INIT_JAVA.mappers;

import com.example.INIT_JAVA.DTOs.request.MovieRequestDto;
import com.example.INIT_JAVA.DTOs.response.MovieResponseDto;
import com.example.INIT_JAVA.domain.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    List<MovieResponseDto> mapToDto(List<Movie> movieResponseDtos);

    Movie mapToDto(MovieRequestDto movieRequestDto);

    MovieResponseDto mapToDto(Movie movie);
}
