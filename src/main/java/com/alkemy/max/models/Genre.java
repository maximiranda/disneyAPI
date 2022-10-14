package com.alkemy.max.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;
    private String name;
    private String image;
    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER)
    private Set<Film> films;

    public Genre() {
    }

    public Genre(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void addFilms(Film film) {
        film.setGenre(this);
        films.add(film);
    }
}
