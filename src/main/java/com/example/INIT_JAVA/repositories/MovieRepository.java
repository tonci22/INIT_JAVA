package com.example.INIT_JAVA.repositories;

import com.example.INIT_JAVA.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
