package com.alkemy.max;

import com.alkemy.max.models.*;
import com.alkemy.max.models.Character;
import com.alkemy.max.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class DisneyApiApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(DisneyApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(FilmRepository filmRepository, CharacterRepository characterRepository, GenreRepository genreRepository, CharacterFilmRepository characterFilmRepository, AdminRepository adminRepository){
		return (args) -> {
			Admin admin = new Admin("Maxi", "admin@gmail.com", passwordEncoder.encode("maxi12345"));
			Genre action = new Genre("Action", "./action");
			Genre fantasy = new Genre("fantasy", "./fantasy");
			Film film1 = new Film("Thor: Love and Thunder", "./hola",new Date(), Rating.TREE, action);
			Film film2 = new Film("Obi-Wan Kenobi", "./obi",new Date(), Rating.FIVE, fantasy);
			Character character1 = new Character("/hoa", "Thor", 27, 100, "Thor Odinson is the Asgardian God of Thunder, the former king of Asgard and New Asgard, and a founding member of the Avengers");
			Character character2 = new Character("/hoa", "Obi", 45, 100, "Thor Odinson is the Asgardian God of Thunder, the former king of Asgard and New Asgard, and a founding member of the Avengers");
			Character character3 = new Character("/hoa", "Leia", 23, 100, "Thor Odinson is the Asgardian God of Thunder, the former king of Asgard and New Asgard, and a founding member of the Avengers");
			Character character4 = new Character("/hoa", "Darth Vader", 50, 100, "Thor Odinson is the Asgardian God of Thunder, the former king of Asgard and New Asgard, and a founding member of the Avengers");
			Character character5 = new Character("/hoa", "Jane Foster", 29, 100, "Thor Odinson is the Asgardian God of Thunder, the former king of Asgard and New Asgard, and a founding member of the Avengers");
			CharacterFilm characterFilm1 = new CharacterFilm(character1, film1);
			CharacterFilm characterFilm2 = new CharacterFilm(character2, film2);
			CharacterFilm characterFilm3 = new CharacterFilm(character3, film2);
			CharacterFilm characterFilm4 = new CharacterFilm(character4, film2);
			CharacterFilm characterFilm5 = new CharacterFilm(character5, film1);

			// SAVES
			genreRepository.save(action);
			genreRepository.save(fantasy);
			filmRepository.save(film1);
			filmRepository.save(film2);
			characterRepository.save(character1);
			characterRepository.save(character2);
			characterRepository.save(character3);
			characterRepository.save(character4);
			characterRepository.save(character5);
			characterFilmRepository.save(characterFilm1);
			characterFilmRepository.save(characterFilm2);
			characterFilmRepository.save(characterFilm3);
			characterFilmRepository.save(characterFilm4);
			characterFilmRepository.save(characterFilm5);
			adminRepository.save(admin);
		};
	}
}
