package com.example.INIT_JAVA.services.Implementation;

import com.example.INIT_JAVA.DTOs.request.CategoryRequestDto;
import com.example.INIT_JAVA.DTOs.request.MovieRequestDto;
import com.example.INIT_JAVA.DTOs.response.CategoryResponseDto;
import com.example.INIT_JAVA.DTOs.response.MovieResponseDto;
import com.example.INIT_JAVA.domain.Category;
import com.example.INIT_JAVA.domain.Movie;
import com.example.INIT_JAVA.exceptions.RepositoryNotFoundException;
import com.example.INIT_JAVA.mappers.CategoryMapper;
import com.example.INIT_JAVA.mappers.MovieMapper;
import com.example.INIT_JAVA.repositories.MovieRepository;
import com.example.INIT_JAVA.services.CategoryService;
import com.example.INIT_JAVA.services.MovieService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    public MovieServiceImpl(final MovieRepository movieRepository, final MovieMapper movieMapper,
                            @Qualifier("categoryServiceImpl") final CategoryService categoryService,
                            final CategoryMapper categoryMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<MovieResponseDto> getAll() {
        return movieMapper.mapToDto(movieRepository.findAll());
    }

    @Override
    public MovieResponseDto add(MovieRequestDto movieRequestDto) {

        Movie movie = movieMapper.mapToDto(movieRequestDto);

        List<Category> allExistingCategories = categoryMapper.
                mapToDtoList(findAllExistingCategories(movieRequestDto.getCategories()));

        movie.setCategories(allExistingCategories);

        movieRepository.save(movie);

        return movieMapper.mapToDto(movie);
    }

    private List<CategoryResponseDto> findAllExistingCategories(List<CategoryRequestDto> categoryRequestDtos) {
        List<CategoryResponseDto> existingCategories;

        List<String> categoryNames = new ArrayList<>();

        categoryRequestDtos.forEach(categoryRequestDto -> {
            categoryNames.add(categoryRequestDto.getName());
        });

        existingCategories = categoryService.findByCategoryNames(categoryNames);

        if (existingCategories.size() < categoryRequestDtos.size())
            throw new RepositoryNotFoundException("Category does not exist");

        return existingCategories;
    }

    @Override
    public MovieResponseDto update(Long id, MovieRequestDto movieRequestDto) {
        movieRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Movie ID not found"));

        List<Category> allExistingCategories = categoryMapper.
                mapToDtoList(findAllExistingCategories(movieRequestDto.getCategories()));

        Movie movie = new Movie();
        movie.setName(movieRequestDto.getName());
        movie.setId(id);
        movie.setCategories(allExistingCategories);

        movieRepository.save(movie);

        return movieMapper.mapToDto(movie);
    }


    @Override
    public void deleteById(Long id) {
        movieRepository.findById(id).orElseThrow(() -> new RepositoryNotFoundException("Movie Id not found"));
        movieRepository.deleteById(id);
    }
}
