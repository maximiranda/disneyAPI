package com.alkemy.max.dtos;

import com.alkemy.max.models.Film;
import lombok.Getter;

import java.util.Date;


@Getter
public class FilmsDTO {
    private Long id;
    private String title, image;
    private Date creationDate;

    public FilmsDTO(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.creationDate = film.getCreationDate();
        this.image = film.getImage();
    }
}
