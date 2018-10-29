package com.stackroute.moviedb.repository;

import com.stackroute.moviedb.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;
    Movie movie;
    ArrayList<Movie> list;

    @Before
    public void setUp() throws Exception {
        movie = new Movie();
        movie.setImdbId("tt5474456");
        movie.setMovieTitle("Fight Club");
        movie.setPosterUrl("https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwixn4OswPjdAhUDcCsKHT0VCUoQjRx6BAgBEAU&url=https%3A%2F%2Fwww.foxmovies.com%2Fmovies%2Ffight-club&psig=AOvVaw0tL2Cnyo7lT3kEMFKgv_CU&ust=1539145072202448");
        movie.setRating(9.1);
        movie.setYearOfRelease("1999");
        movie.setComments("insomnia is a bitch");
        list = new ArrayList<>();
        list.add(movie);

    }

    @After
    public void tearDown()throws Exception{

        movieRepository.deleteAll();
    }

    @Test
    public void testSaveMovie(){
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertEquals("tt5474456",fetchUser.getImdbId());

    }

    @Test
    public void testSaveMovieFailure(){
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertNotNull(fetchMovie);
    }

    @Test
    public void findMovieByMovieTitleTest() {
        movieRepository.save(movie);
        Movie expected= movieRepository.findMovieByMovieTitle(movie.getMovieTitle());
        ArrayList<Movie>  actual = list;
        Assert.assertEquals(expected.getMovieTitle(),actual.get(0).getMovieTitle());
    }
    @Test
    public void findAllTest(){
        movieRepository.save(movie);
        List<Movie> expected= movieRepository.findAll();
        ArrayList<Movie> actual =list;
        Assert.assertEquals(expected.get(0).toString(),actual.get(0).toString());
    }

    @Test
    public void testDeleteMovie(){
        movieRepository.save(movie);
        movieRepository.delete(movie);
        Assert.assertEquals(Optional.empty(),movieRepository.findById(movie.getImdbId()));
    }

}


