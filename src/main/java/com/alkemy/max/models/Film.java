package com.alkemy.max.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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

    @OneToMany(mappedBy="film", orphanRemoval = true, fetch=FetchType.EAGER)
    Set<CharacterFilm> characterFilms = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="genre_id")
    private Genre genre;

    public Film() {
    }

    public Film(String title, String image, Date creationDate, Rating rating, Genre genre) {
        this.title = title;
        this.image = image;
        this.creationDate = creationDate;
        this.rating = rating;
        this.genre = genre;
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


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<CharacterFilm> getCharacterFilms() {
        return characterFilms;
    }

    public void addCharacterFilms(CharacterFilm characterFilm) {
        characterFilm.setFilm(this);
        characterFilms.add(characterFilm);
    }
}
