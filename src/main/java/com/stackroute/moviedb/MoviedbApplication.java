package com.stackroute.moviedb;

import com.stackroute.moviedb.domain.Movie;
import com.stackroute.moviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class MoviedbApplication  implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

	@Autowired
	MovieRepository movieRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Movie movie = new Movie("tt0075314","Taxi Driver","https://m.media-amazon.com/images/M/MV5BM2M1MmVhNDgtNmI0YS00ZDNmLTkyNjctNTJiYTQ2N2NmYzc2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,658,1000_AL_.jpg", 9.1,"1976","you talking to me ");
		movieRepository.save(movie);
		Movie movie2 = new Movie("tt0468569", "Dark Knight", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_QL50_SY1000_CR0,0,675,1000_AL_.jpg", 9.0, "2008", "madness is like gravity");
		movieRepository.save(movie2);
	}
	@Override
	public void run(String... args){
		Movie movie = new Movie("tt0110912", "Pulp Fiction", "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,686,1000_AL_.jpg", 8.9, "1994", "English motherfucker");
		movieRepository.save(movie);
		Movie movie2 = new Movie("tt1375666", "Inception", "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_QL50_SY1000_CR0,0,675,1000_AL_.jpg", 8.8, "2010", "Dreams feel real while we are in them");
		movieRepository.save(movie2);
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviedbApplication.class, args);
	}
}
