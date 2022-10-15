package com.alkemy.max.services.implement;

import com.alkemy.max.models.Genre;
import com.alkemy.max.repositories.GenreRepository;
import com.alkemy.max.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GenreServiceImplement implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre getById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
    public Set<Genre> getAll(){
        return new HashSet<>(genreRepository.findAll());
    }
    public void save(Genre genre){
        genreRepository.save(genre);
    }
}