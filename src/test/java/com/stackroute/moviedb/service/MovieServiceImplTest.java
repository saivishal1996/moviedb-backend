package com.stackroute.moviedb.service;


import com.stackroute.moviedb.domain.Movie;
import com.stackroute.moviedb.exceptions.MovieAlreadyExistsException;
import com.stackroute.moviedb.exceptions.MovieNotFoundException;
import com.stackroute.moviedb.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {

    Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
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

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie)any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }


    @Test
    public void getAllMoviesTest() {
        List<Movie> movies = new ArrayList<Movie>();
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> returned = movieService.getAllMovies();

        verify(movieRepository, times(1)).findAll();
        verifyNoMoreInteractions(movieRepository);
        assertEquals(movies, returned);
    }

    @Test
    public void getMovieTest() {
        when(movieRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(movie));

        Movie returned = movieService.getMovie("tt5474456");

        verify(movieRepository, times(1)).findById(any());
        verifyNoMoreInteractions(movieRepository);

        assertEquals(movie, returned);
    }


    @Test
    public void deleteMovieTest() throws MovieNotFoundException {

        when(movieService.deleteMovie(anyString())).thenReturn(true);
        doNothing().when(movieRepository).deleteById(anyString());
        Boolean b = movieService.deleteMovie(movie.getImdbId());
        Assert.assertEquals(true,b);
        verify(movieRepository,times(1)).deleteById(movie.getImdbId());

    }

    @Test(expected = NoSuchElementException.class)
    public void updateMovieTest() throws MovieNotFoundException {

        Movie movie1=new Movie(movie.getImdbId(),"Fight Club","http://robot2/3d/poster",9.3,"1999" , "insomnia mofo");
        when(movieRepository.existsById(anyString())).thenReturn(true);
        when(movieRepository.findById(anyString()).get()).thenReturn(movie);
        Movie fetchMovie= movieService.updateMovie(movie.getImdbId(),movie1);
        Assert.assertNotEquals(null,fetchMovie);
        verify(movieRepository,times(1)).existsById(movie.getImdbId());

    }

    @Test
    public void getMovieByMovieTitleTest() throws MovieNotFoundException {
        movieRepository.save(movie);
        when(movieRepository.findMovieByMovieTitle(any())).thenReturn(movie);
        Movie movie1 = movieService.getMovieByMovieTitle(movie.getMovieTitle());
        Assert.assertEquals(movie,movie1);
    }

}
