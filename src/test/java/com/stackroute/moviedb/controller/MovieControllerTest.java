//package com.stackroute.moviedb.controller;
//
//import com.stackroute.moviedb.controller.MovieController;
//import com.stackroute.moviedb.domain.Movie;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.moviedb.exceptions.MovieAlreadyExistsException;
//import com.stackroute.moviedb.exceptions.MovieNotFoundException;
//import com.stackroute.moviedb.service.MovieService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.exceptions.base.MockitoException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class MovieControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Movie movie;
//    @Mock
//    private MovieService movieService;
//    @InjectMocks
//    private MovieController movieController;
//
//    private List<Movie> list = null;
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
//        movie = new Movie("tt0110912", "Pulp Fiction", "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,686,1000_AL_.jpg", 8.9, "1994", "English motherfucker");
//        list = new ArrayList();
//        list.add(movie);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void saveMovieTest() throws Exception {
//        when(movieService.saveMovie(any())).thenReturn(movie);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getAllMoviesTest() throws Exception{
//
//        when(movieService.getAllMovies()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getMovieTest() throws Exception {
//        when(movieService.getMovie(any())).thenReturn(movie);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/testId")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getByMovieTitleTest() throws Exception {
//        when(movieService.getMovieByMovieTitle(any())).thenReturn((Movie) list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/name/testName")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String asJsonString(final Object obj)
//    {
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//    @Test
//    public void testUpdateMovieByIdSuccess() throws Exception {
//        when(movieService.updateMovie((String)any(),(Movie)any())).thenReturn(movie);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie/{id}",movie.getImdbId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//    }
//    @Test(expected = MockitoException.class)
//    public void testUpdateMovieByIdFailure() throws Exception {
//        when(movieService.updateMovie((String)any(),(Movie)any())).thenThrow(MovieNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie/{id}",movie.getImdbId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void testDeleteMovieByIdSuccess() throws Exception {
//        when(movieService.deleteMovie((String)any())).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/{id}",movie.getImdbId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test(expected = MockitoException.class)
//    public void testDeleteMovieByIdFailure() throws Exception {
//        when(movieService.deleteMovie((String)any())).thenThrow(MovieNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/{id}",movie.getImdbId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//
//}