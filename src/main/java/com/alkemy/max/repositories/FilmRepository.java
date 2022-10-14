package com.alkemy.max.repositories;

import com.alkemy.max.models.Film;
import com.alkemy.max.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film,Long> {
    public Set<Film> findAllByTitle(String title);
    public Set<Film> findAllByGenre(Genre genre);
    public Set<Film> findAllByOrderByIdAsc();
    public Set<Film> findAllByOrderByIdDesc();

}
