package com.alkemy.max.repositories;

import com.alkemy.max.models.CharacterFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CharacterFilmRepository extends JpaRepository<CharacterFilm, Long> {

}
