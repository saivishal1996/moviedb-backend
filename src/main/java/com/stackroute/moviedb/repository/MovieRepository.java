package com.stackroute.moviedb.repository;

import com.stackroute.moviedb.domain.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {

@Query("{ 'movieTitle' : ?0 }")
Movie findMovieByMovieTitle(String movieTitle);

}
