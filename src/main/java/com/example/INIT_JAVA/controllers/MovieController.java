package com.example.INIT_JAVA.controllers;

import com.example.INIT_JAVA.DTOs.request.MovieRequestDto;
import com.example.INIT_JAVA.DTOs.response.MovieResponseDto;
import com.example.INIT_JAVA.services.MovieService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(@Qualifier("movieServiceImpl") final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAll());
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.add(movieRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable("id") Long id,
                                                        @RequestBody MovieRequestDto movieRequestDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieService.update(id, movieRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable("id") Long id) {
        movieService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted");
    }
}
