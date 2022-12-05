package com.example.INIT_JAVA.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "movies_categories", joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<User> users = new HashSet<>();
}
