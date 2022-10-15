package com.alkemy.max.dtos;

import com.alkemy.max.models.Film;
import com.alkemy.max.models.Rating;
import lombok.Getter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class FilmDTO {
    private Long id;
    private String title, image;
    private Date creationDate;
    private Rating rating;
    private Set<String> characters = new HashSet<>();

    public FilmDTO(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.creationDate = film.getCreationDate();
        this.rating = film.getRating();
        this.image = film.getImage();
        this.characters = film.getCharacterFilms().stream().map(characterFilm -> new CharacterFilmDTO(characterFilm).getCharacter()).collect(Collectors.toSet());
    }
}
