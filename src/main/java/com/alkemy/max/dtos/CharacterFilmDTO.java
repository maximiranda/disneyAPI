package com.alkemy.max.dtos;

import com.alkemy.max.models.CharacterFilm;

public class CharacterFilmDTO {

    private Long id;
    private String film;
    private String character;

    public CharacterFilmDTO(CharacterFilm characterFilm) {
        this.character = characterFilm.getCharacter().getName();
        this.film = characterFilm.getFilm().getTitle();
        this.id = characterFilm.getId();
    }

    public Long getId() {
        return id;
    }

    public String getFilm() {
        return film;
    }

    public String getCharacter() {
        return character;
    }
}
