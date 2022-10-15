package com.alkemy.max.services;

import com.alkemy.max.models.Character;

import java.util.Set;

public interface CharacterService {
        public Character getById(Long id);
        public Set<Character> getAll();
        public Set<Character> getAllByName(String name);
        public Set<Character> getAllByAge(Integer age);
        public void save(Character character);
        public void delete(Character character);

    }
