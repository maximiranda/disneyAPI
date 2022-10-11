package com.alkemy.max.dtos;

import com.alkemy.max.models.Character;

public class CharacterDTO {
    private Long id;
    private String name, image, story;
    private Integer age, weight;

    public CharacterDTO(Character character) {
        this.id = character.getId();
        this.age = character.getAge();
        this.name = character.getName();
        this.image = character.getImage();
        this.story = character.getStory();
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
}
