package com.alkemy.max.controllers;

import com.alkemy.max.dtos.CharacterDTO;
import com.alkemy.max.repositories.CharacterRepository;
import com.alkemy.max.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")

public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private FilmRepository filmRepository;
    @GetMapping("/characters")
    public Set<CharacterDTO> getCharacters(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age,@RequestParam(required = false) Long movie){
        if (name != null){
            return characterRepository.findAllByName(name).stream().map(CharacterDTO::new).collect(Collectors.toSet());
        }if (age != null){
            return characterRepository.findAllByAge(age).stream().map(CharacterDTO::new).collect(Collectors.toSet());
        } if(movie != null){
            return filmRepository.findById(movie).get().getCharacterFilms().stream().map(characterFilm -> new CharacterDTO(characterFilm.getCharacter())).collect(Collectors.toSet());
        }
        return characterRepository.findAll().stream().map(CharacterDTO::new).collect(Collectors.toSet());
    }
    @GetMapping("/characters/{id}")
    public  CharacterDTO getCharacter(@PathVariable Long id){
        return new CharacterDTO(Objects.requireNonNull(characterRepository.findById(id).orElse(null)));
    }
}
