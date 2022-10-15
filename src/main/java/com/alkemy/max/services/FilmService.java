package com.alkemy.max.services;

import com.alkemy.max.models.Film;
import com.alkemy.max.models.Genre;

import java.util.Set;

public interface FilmService {
    public Film getById(Long id);
    public Set<Film> getAll();
    public Set<Film> getAllByTitle(String title);
    public Set<Film> getAllByGenre(Genre genre);
    public void save(Film film);
    public void delete(Film film);

}
