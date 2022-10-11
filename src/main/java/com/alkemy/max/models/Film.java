package com.alkemy.max.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;
    private String title;
    private String image;
    private Date creationDate;
    private Rating rating;

    @OneToMany(mappedBy="film", fetch=FetchType.EAGER)
    private Set<Character> characters = new HashSet<>();

    public Film() {
    }

    public Film(String title, String image, Date creationDate, Rating rating) {
        this.title = title;
        this.image = image;
        this.creationDate = creationDate;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Character> getCharacters() {
        return characters;
    }
    public void addCharacter(Character character) {
        character.setFilm(this);
        characters.add(character);
    }
}
