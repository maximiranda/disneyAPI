package com.alkemy.max.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
public class CharacterFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="character_id")
    private Character character;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="film_id")
    private Film film;

    public CharacterFilm() {
    }

    public CharacterFilm(Character character, Film film) {
        this.character = character;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
