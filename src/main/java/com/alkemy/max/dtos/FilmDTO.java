package com.alkemy.max.dtos;

import com.alkemy.max.models.Film;
import com.alkemy.max.models.Rating;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmDTO {
    private Long id;
    private String title;
    private Date creationDate;
    private Rating rating;
    private Set<String> characters = new HashSet<>();

    public FilmDTO(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.creationDate = film.getCreationDate();
        this.rating = film.getRating();
        this.characters = film.getCharacterFilms().stream().map(characterFilm -> new CharacterFilmDTO(characterFilm).getCharacter()).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Rating getRating() {
        return rating;
    }

    public Set<String> getCharacters() {
        return characters;
    }
}
