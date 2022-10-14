package com.alkemy.max.repositories;

import com.alkemy.max.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface CharacterRepository extends JpaRepository<Character, Long> {
    public Set<Character> findAllByName(String name);
    public Set<Character> findAllByAge(Integer age);
}
