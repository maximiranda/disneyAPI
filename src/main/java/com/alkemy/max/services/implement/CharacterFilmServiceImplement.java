package com.alkemy.max.services.implement;

import com.alkemy.max.models.CharacterFilm;
import com.alkemy.max.repositories.CharacterFilmRepository;
import com.alkemy.max.services.CharacterFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterFilmServiceImplement implements CharacterFilmService {
    @Autowired
    private CharacterFilmRepository characterFilmRepository;
    @Override
    public void save(CharacterFilm characterFilm){
        characterFilmRepository.save(characterFilm);
    }
}
