package com.alkemy.max.dtos;

import com.alkemy.max.models.Character;
import lombok.Getter;

@Getter
public class CharactersDTO {
    private Long id;
    private String name, image;
    public CharactersDTO(Character character){
        this.id = character.getId();
        this.name = character.getName();
        this.image = character.getImage();
    }
}
