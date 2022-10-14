package com.alkemy.max.controllers;

import com.alkemy.max.dtos.FilmDTO;
import com.alkemy.max.repositories.FilmRepository;
import com.alkemy.max.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private GenreRepository genreRepository;
    @GetMapping("/movies")
    public Set<FilmDTO> getFilms(@RequestParam(required = false) String name, @RequestParam(required = false) Long genre,@RequestParam(required = false) String order){
        if (name != null){
            return filmRepository.findAllByTitle(name).stream().map(FilmDTO::new).collect(Collectors.toSet());
        }
        if (genre != null){
            return Objects.requireNonNull(genreRepository.findById(genre).orElse(null)).getFilms().stream().map(FilmDTO::new).collect(Collectors.toSet());
        }
        if (order != null){
            if (order.equals("ASC")){
                return filmRepository.findAllByOrderByIdAsc().stream().map(FilmDTO::new).collect(Collectors.toSet());
            }

            return filmRepository.findAllByOrderByIdDesc().stream().map(FilmDTO::new).collect(Collectors.toSet());
        }
        return filmRepository.findAll().stream().map(FilmDTO::new).collect(Collectors.toSet());
    }
    @GetMapping("/movies/{id}")
    public FilmDTO getFilm(@PathVariable Long id){
        return new FilmDTO(Objects.requireNonNull(filmRepository.findById(id).orElse(null)));
    }
}
