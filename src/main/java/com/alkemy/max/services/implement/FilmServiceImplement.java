package com.alkemy.max.services.implement;

import com.alkemy.max.models.Film;
import com.alkemy.max.models.Genre;
import com.alkemy.max.repositories.FilmRepository;
import com.alkemy.max.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class FilmServiceImplement implements FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Override
    public Film getById(Long id){
        return filmRepository.findById(id).orElse(null);
    }
    @Override
    public Set<Film> getAll(){
        return new HashSet<>(filmRepository.findAll());
    }
    @Override
    public Set<Film> getAllByTitle(String title){
        return filmRepository.findAllByTitle(title);
    }
    @Override
    public Set<Film> getAllByGenre(Genre genre){
        return filmRepository.findAllByGenre(genre);
    }
    @Override
    public void save(Film film){
        filmRepository.save(film);
    }
    @Override
    public void delete(Film film){
        filmRepository.delete(film);
    }
}
