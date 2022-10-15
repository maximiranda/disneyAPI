package com.alkemy.max.services;

import com.alkemy.max.models.Genre;

import java.util.Set;

public interface GenreService {
    public Genre getById(Long id);
    public Set<Genre> getAll();
    public void save(Genre genre);
}
