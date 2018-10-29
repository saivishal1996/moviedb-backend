package com.stackroute.moviedb.service;

import com.stackroute.moviedb.domain.Movie;
import com.stackroute.moviedb.exceptions.MovieAlreadyExistsException;
import com.stackroute.moviedb.exceptions.MovieNotFoundException;

import java.util.List;

public interface MovieService {

     Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

     List<Movie> getAllMovies();

     Movie updateMovie(String id,Movie movie);

     Boolean deleteMovie(String id);

     Movie getMovie(String id);

     Movie getMovieByMovieTitle(String regexp) throws MovieNotFoundException;
}
