package com.stackroute.moviedb.service;

import com.stackroute.moviedb.domain.Movie;
import com.stackroute.moviedb.exceptions.MovieAlreadyExistsException;
import com.stackroute.moviedb.exceptions.MovieNotFoundException;
import com.stackroute.moviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieServiceImpl() {
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getImdbId())) {
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        Movie savedMovie = movieRepository.save(movie);

        if(savedMovie == null){
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        return savedMovie;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }


    @Override
    public Movie updateMovie(String id, Movie movie) {

        Movie updatedMovie=null;
        if(movieRepository.existsById(id)) {
            updatedMovie = movieRepository.findById(id).get();
            updatedMovie.setMovieTitle(movie.getMovieTitle());
            updatedMovie.setPosterUrl(movie.getPosterUrl());
            updatedMovie.setRating(movie.getRating());
            updatedMovie.setYearOfRelease(movie.getYearOfRelease());
            updatedMovie.setComments(movie.getComments());

        }
        Movie savedMovie=movieRepository.save(updatedMovie);
        return savedMovie;
    }

    @Override
    public Boolean deleteMovie(String id) {
        movieRepository.deleteById(id);
            if (movieRepository.existsById(id))
                return false;
            return true;

    }

    @Override
    public Movie getMovie(String id) {
        final Movie movie = movieRepository.findById(id).get();
        return movie;
    }

    @Override
    public Movie getMovieByMovieTitle(String movieTitle) throws MovieNotFoundException {
        Movie movie = movieRepository.findMovieByMovieTitle(movieTitle);

        if(movie == null){
            throw new MovieNotFoundException("movie not found");
        }
        return movie;
    }
}
