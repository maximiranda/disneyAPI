package com.alkemy.max.controllers;

import com.alkemy.max.models.Character;
import com.alkemy.max.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api")

public class CharacterController {

    private @Autowired CharacterRepository characterRepository;

    @GetMapping("/characters")
    public Set<Character> getCharacters(){
        return new HashSet<>(characterRepository.findAll());
    }
}
