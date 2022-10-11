package com.alkemy.max;

import com.alkemy.max.models.Character;
import com.alkemy.max.models.Film;
import com.alkemy.max.models.Rating;
import com.alkemy.max.repositories.CharacterRepository;
import com.alkemy.max.repositories.FilmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DisneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisneyApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(FilmRepository filmRepository, CharacterRepository characterRepository){
		return (args) -> {
			Film film1 = new Film("hola", "./hola",new Date(), Rating.EIGHT);
			Character character1 = new Character("/hoa", "Carlos", 29, 70, "Carlos was a good men", film1);
			filmRepository.save(film1);
			characterRepository.save(character1);
		};
	}
}
