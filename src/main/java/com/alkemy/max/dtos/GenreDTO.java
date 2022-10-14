package com.alkemy.max.dtos;

import com.alkemy.max.models.Genre;

import java.util.Set;
import java.util.stream.Collectors;

public class GenreDTO {
    private Long id;
    private String name;
    private String image;
    private Set<FilmDTO> films;

    public GenreDTO(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
        this.image = genre.getImage();
        this.films = genre.getFilms().stream().map(FilmDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Set<FilmDTO> getFilms() {
        return films;
    }
}
