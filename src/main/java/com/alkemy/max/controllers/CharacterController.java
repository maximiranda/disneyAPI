package com.alkemy.max.controllers;

import com.alkemy.max.dtos.CharacterDTO;
import com.alkemy.max.dtos.CharactersDTO;
import com.alkemy.max.models.Character;
import com.alkemy.max.models.CharacterFilm;
import com.alkemy.max.models.Film;
import com.alkemy.max.services.CharacterFilmService;
import com.alkemy.max.services.CharacterService;
import com.alkemy.max.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")

public class CharacterController {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private CharacterFilmService characterFilmService;

    /* Get all character */
    @GetMapping("/characters")
    public List<CharactersDTO> getCharacters(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age, @RequestParam(required = false) Long movie){
        if (name != null){
            return characterService.getAllByName(name).stream().map(CharactersDTO::new).collect(Collectors.toList());
        }if (age != null){
            return characterService.getAllByAge(age).stream().map(CharactersDTO::new).collect(Collectors.toList());
        } if(movie != null){
            return filmService.getById(movie).getCharacterFilms().stream().map(characterFilm -> new CharactersDTO(characterFilm.getCharacter())).collect(Collectors.toList());
        }
        return characterService.getAll().stream().map(CharactersDTO::new).collect(Collectors.toList());
    }
    /* Get characters by id */
    @GetMapping("/characters/{id}")
    public  CharacterDTO getCharacter(@PathVariable Long id){
        return new CharacterDTO(Objects.requireNonNull(characterService.getById(id)));
    }
    /*Create a new character*/
    @PostMapping("/characters")
    public ResponseEntity<Object> createCharacter(
            @RequestParam String name, @RequestParam String image, @RequestParam String story,
            @RequestParam Integer age, @RequestParam Integer weight, @RequestParam(required = false) List<Long> filmIds)
    {
        if(name.isEmpty() || image.isEmpty() || story.isEmpty() || age == null || weight == null){
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }
        Character character = new Character(image, name, age, weight, story);
        characterService.save(character);
        if (filmIds != null){
            for (Long filmId : filmIds){
                Film film = filmService.getById(filmId);
                CharacterFilm characterFilm = new CharacterFilm(character, film);
                characterFilmService.save(characterFilm);
            }
        }
        return new ResponseEntity<>("Character Created", HttpStatus.CREATED);
    }
    /*Modify a Character*/
    @PatchMapping("/characters")
    public ResponseEntity<Object> modifyCharacter(
            @RequestParam Long id,
            @RequestParam(required = false) String name, @RequestParam(required = false) String image, @RequestParam(required = false) String story,
            @RequestParam(required = false) Integer age, @RequestParam(required = false) Integer weight, @RequestParam(required = false) List<Long> filmIds)
    {
        if(name.isEmpty() && image.isEmpty() && story.isEmpty() && age == null && weight == null && filmIds.isEmpty()){
            return new ResponseEntity<>("you must fill in at least one field", HttpStatus.FORBIDDEN);
        }
        if (id == null){
            return new ResponseEntity<>("Select one character", HttpStatus.FORBIDDEN);
        }
        Character character = characterService.getById(id);
        if (character == null){
            return new ResponseEntity<>("the character does not exist", HttpStatus.FORBIDDEN);
        }
        if (!name.isEmpty()){
            character.setName(name);
        }

        if (image != null){
            character.setImage(image);
        }
        if (story != null){
        character.setStory(story);
    }
        if (age != null){
            character.setAge(age);
        }
        if (weight != null){
        character.setWeight(weight);
        }
        if (filmIds != null){
            for (Long filmId : filmIds){
                Film film = filmService.getById(filmId);
                CharacterFilm characterFilm = new CharacterFilm(character, film);
                characterFilmService.save(characterFilm);
            }
        }
        characterService.save(character);
        return new ResponseEntity<>("Character Modify", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/characters")
    public ResponseEntity<Object> deleteCharacter(@RequestParam Long id){
        Character character = characterService.getById(id);
        if (character == null){
            return new ResponseEntity<>("the character does not exist", HttpStatus.FORBIDDEN);
        }
        characterService.delete(character);
        return new ResponseEntity<>("Character Delete", HttpStatus.ACCEPTED);
    }
}
