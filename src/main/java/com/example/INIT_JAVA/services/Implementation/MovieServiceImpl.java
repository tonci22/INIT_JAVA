package com.example.INIT_JAVA.services.Implementation;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.request.MovieRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;
import com.example.INIT_JAVA.DTOs.response.MovieResponseDto;
import com.example.INIT_JAVA.domain.Category;
import com.example.INIT_JAVA.domain.Movie;
import com.example.INIT_JAVA.exceptions.EntityNotFoundException;
import com.example.INIT_JAVA.mappers.CategoryMapper;
import com.example.INIT_JAVA.mappers.MovieMapper;
import com.example.INIT_JAVA.repositories.MovieRepository;
import com.example.INIT_JAVA.services.CategoryService;
import com.example.INIT_JAVA.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @Override
    public List<MovieResponseDto> getAll() {
        return movieMapper.mapToDto(movieRepository.findAll());
    }

    @Override
    public MovieResponseDto add(MovieRequestDto movieRequestDto) {

        Movie movie = movieMapper.mapToDto(movieRequestDto);

        Set<Category> allExistingCategories = categoryMapper.
                mapToDtoList(findAllExistingCategories(movieRequestDto.getCategories()));

        movie.setCategories(allExistingCategories);

        movieRepository.save(movie);

        return movieMapper.mapToDto(movie);
    }

    private Set<CategoryResponseDto> findAllExistingCategories(List<CategoryRequestDto> categoryRequestDtos) {
        Set<CategoryResponseDto> existingCategories;

        Set<String> categoryNames = new HashSet<>();

        categoryRequestDtos.forEach(categoryRequestDto -> categoryNames.add(categoryRequestDto.getName()));

        existingCategories = categoryService.findByCategoryNames(categoryNames);

        if (existingCategories.size() < categoryRequestDtos.size())
            throw new EntityNotFoundException("Category does not exist");

        return existingCategories;
    }

    @Override
    public MovieResponseDto update(Long id, MovieRequestDto movieRequestDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie ID not found"));

        movie.setName(movieRequestDto.getName());

        Set<Category> allExistingCategories = categoryMapper.
                mapToDtoList(findAllExistingCategories(movieRequestDto.getCategories()));

        movie.setCategories(allExistingCategories);

        movieRepository.save(movie);

        return movieMapper.mapToDto(movie);
    }


    @Override
    public void deleteById(Long id) {
        movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie Id not found"));
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieResponseDto> findAllMoviesByName(String movieName, Pageable pageable) {

        List<Movie> movies = movieRepository.findByNameContains(movieName, pageable);

        return movieMapper.mapToDto(movies);
    }
}
