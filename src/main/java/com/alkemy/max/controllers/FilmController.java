package com.alkemy.max.controllers;

import com.alkemy.max.dtos.FilmDTO;

import com.alkemy.max.dtos.FilmsDTO;
import com.alkemy.max.models.*;
import com.alkemy.max.models.Character;
import com.alkemy.max.services.CharacterFilmService;
import com.alkemy.max.services.CharacterService;
import com.alkemy.max.services.FilmService;
import com.alkemy.max.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private CharacterFilmService characterFilmService;
    @GetMapping("/movies")
    public List<FilmsDTO> getFilms(@RequestParam(required = false) String name, @RequestParam(required = false) Long genre,@RequestParam(required = false) String order){
        if (name != null){
            return filmService.getAllByTitle(name).stream().map(FilmsDTO::new).collect(Collectors.toList());
        }
        if (genre != null){
            return genreService.getById(genre).getFilms().stream().map(FilmsDTO::new).collect(Collectors.toList());
        }
        if (order != null){
            List<FilmsDTO> filmDTOS = filmService.getAll().stream().map(FilmsDTO::new).collect(Collectors.toList());
            if (order.equals("ASC")){
                filmDTOS.sort(Comparator.comparing(FilmsDTO::getCreationDate));
                return filmDTOS;
            }
            if (order.equals("DESC")){
                filmDTOS.sort(Comparator.comparing(FilmsDTO::getCreationDate).reversed());
                return filmDTOS;
            }


        }
        return filmService.getAll().stream().map(FilmsDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/movies/{id}")
    public FilmDTO getFilm(@PathVariable Long id){
        Film film = filmService.getById(id);
        if (film == null){
            return null;
        }
        return new FilmDTO(film);
    }
    @PostMapping("/movies")
    public ResponseEntity<Object> createMovie(
            @RequestParam String title, @RequestParam Date creationDate, @RequestParam String image,
            @RequestParam Long genreId, @RequestParam Rating rating,
            @RequestParam(required = false) List<Long> characterIds){

        if (title.isEmpty() || creationDate == null || image.isEmpty() || rating == null || genreId == null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        Genre genre = genreService.getById(genreId);
        if (genre == null){
            return new ResponseEntity<>("Genre does not exist", HttpStatus.FORBIDDEN);
        }
        Film film = new Film(title, image, creationDate, rating, genre);
        filmService.save(film);

        if (characterIds != null){
            for (Long id : characterIds){
                Character character = characterService.getById(id);
                CharacterFilm characterFilm = new CharacterFilm(character, film);
                characterFilmService.save(characterFilm);
            }
        }
        return new ResponseEntity<>("Movie Created", HttpStatus.CREATED);
    }
    @PatchMapping("/movies")
    public ResponseEntity<Object> modifyMovie(
            @RequestParam Long id,
            @RequestParam(required = false) String title, @RequestParam(required = false) String image, @RequestParam(required = false) Rating rating,
            @RequestParam(required = false) Long genreId, @RequestParam(required = false) List<Long> characterIds)
    {
        if(title.isEmpty() && image.isEmpty() && rating == null && genreId == null && characterIds.isEmpty()){
            return new ResponseEntity<>("you must fill in at least one field", HttpStatus.FORBIDDEN);
        }
        if (id == null){
            return new ResponseEntity<>("Select one character", HttpStatus.FORBIDDEN);
        }
        Film film = filmService.getById(id);
        if (film == null){
            return new ResponseEntity<>("the film does not exist", HttpStatus.FORBIDDEN);
        }
        if (!title.isEmpty()){
            film.setTitle(title);
        }
        if (image != null){
            film.setImage(image);
        }
        if (genreId != null){
            Genre genre = genreService.getById(genreId);
            if (genre != null){

                film.setGenre(genre);
            }
        }
        if (rating != null){
            film.setRating(rating);
        }

        if (characterIds != null){
            for (Long characterId : characterIds){
                Character character = characterService.getById(characterId);
                CharacterFilm characterFilm = new CharacterFilm(character, film);
                characterFilmService.save(characterFilm);
            }
        }
        filmService.save(film);
        return new ResponseEntity<>("Movie Modify", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/movies")
    public ResponseEntity<Object> deleteMovie(@RequestParam Long id){
        Film film = filmService.getById(id);
        if (film == null){
            return new ResponseEntity<>("the film does not exist", HttpStatus.FORBIDDEN);
        }
        filmService.delete(film);
        return new ResponseEntity<>("Film Delete", HttpStatus.ACCEPTED);
    }
}
