package com.example.INIT_JAVA.repositories;

import com.example.INIT_JAVA.domain.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByNameContains(String movie, Pageable pageable);
}