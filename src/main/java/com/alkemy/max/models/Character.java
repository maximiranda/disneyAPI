package com.alkemy.max.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
    @ManyToOne
    private Film film;

    public Character() {
    }

    public Character(String image, String name, Integer age, Integer weight, String story, Film film){
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.story = story;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
