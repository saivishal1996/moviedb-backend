package com.stackroute.moviedb.controller;

import com.stackroute.moviedb.domain.Movie;
import com.stackroute.moviedb.exceptions.MovieAlreadyExistsException;
import com.stackroute.moviedb.exceptions.MovieNotFoundException;
import com.stackroute.moviedb.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/v2")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);


    private MovieService movieService;

    public MovieController() {
    }

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<String>("movie saved", HttpStatus.CREATED);
            logger.info("movie has been saved");
        }catch(MovieAlreadyExistsException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error("error has been occured");
        }
        return responseEntity;
    }

    @GetMapping("movie")
    public ResponseEntity<?> getAllMovies(){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
            logger.info("all movies sent");
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error("error occured");
        }
        return responseEntity;
    }

    @PostMapping("movie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") String id, @RequestBody Movie movie ){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<Movie>(movieService.updateMovie(id,movie), HttpStatus.CREATED);
            logger.info("movie updated");
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error("error occured");
        }
        return responseEntity;
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try{
            movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<String>("movie deleted", HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movie/{id}")
    public ResponseEntity<?> getMovie(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<Movie>(movieService.getMovie(id) , HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movie/name/{movieTitle}")
    public ResponseEntity<?> getByMovieTitle(@PathVariable("movieTitle") String movieTitle){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<Movie>(movieService.getMovieByMovieTitle(movieTitle) , HttpStatus.OK);
        }catch (MovieNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
