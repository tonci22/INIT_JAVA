package com.example.INIT_JAVA.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "movies_categories", joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<User> users = new ArrayList<>();
}
