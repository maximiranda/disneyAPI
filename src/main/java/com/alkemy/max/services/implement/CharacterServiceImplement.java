package com.alkemy.max.services.implement;

import com.alkemy.max.models.Character;
import com.alkemy.max.repositories.CharacterRepository;
import com.alkemy.max.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class CharacterServiceImplement implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;
    @Override
    public Character getById(Long id){
        return characterRepository.findById(id).orElse(null);
    }
    @Override
    public Set<Character> getAllByName(String name){
        return characterRepository.findAllByName(name);
    }
    @Override
    public Set<Character> getAllByAge(Integer age){
        return characterRepository.findAllByAge(age);
    }
    @Override
    public Set<Character> getAll(){
        return new HashSet<>(characterRepository.findAll());
    }
    @Override
    public void save(Character character){
        characterRepository.save(character);
    }
    @Override
    public void delete(Character character){
        characterRepository.delete(character);
    }
}
