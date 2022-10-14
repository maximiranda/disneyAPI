package com.alkemy.max.dtos;

import com.alkemy.max.models.Character;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CharacterDTO {
    private Long id;
    private String name, image, story;
    private Integer age, weight;
    private Set<String> films = new HashSet<>();

    public CharacterDTO(Character character) {
        this.weight = character.getWeight();
        this.id = character.getId();
        this.age = character.getAge();
        this.name = character.getName();
        this.image = character.getImage();
        this.story = character.getStory();
        this.films = character.getCharacterFilms().stream().map(characterFilm -> new CharacterFilmDTO(characterFilm).getFilm()).collect(Collectors.toSet());
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

    public String getStory() {
        return story;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getWeight() {
        return weight;
    }

    public Set<String> getFilms() {
        return films;
    }
}
