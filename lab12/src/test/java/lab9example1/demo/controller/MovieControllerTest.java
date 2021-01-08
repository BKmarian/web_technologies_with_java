package lab9example1.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab9example1.demo.exception.NotFoundMovieException;
import lab9example1.demo.model.Movie;
import lab9example1.demo.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Get a movie with an existing id")
    public void testGetMovieByIdHappyFlow() throws Exception {
        int id = 2;
        Movie movie = new Movie(id, "Movie6", "CRIME", Date.valueOf("2014-03-12"), 6.4f);

        when(movieService.findMovieById(id)).thenReturn(movie);

        mockMvc.perform(get("/movie/getbyid/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(movie.getName()))
        .andExpect(jsonPath("$.type").value(movie.getType()));
    }

    @Test
    @DisplayName("Get a movie with an non-existing id")
    public void testGetMovieByIdBadFlow() throws Exception {
        int id = 10;
        
        when(movieService.findMovieById(id)).thenThrow(new NotFoundMovieException("There is no movie withe the requested id!"));

        mockMvc.perform(get("/movie/getbyid/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Get multiple movies from a type")
    public void testGetMoviesByTypeHappyFlow() throws Exception {
        String type = "CRIME";
        List<Movie> movies = List.of(
                new Movie(2, "Movie2", "CRIME", Date.valueOf("2014-03-12"), 6.4f),
                new Movie(3, "Movie3", "CRIME", Date.valueOf("2016-03-12"), 4.4f));

        when(movieService.findMoviesByType(type)).thenReturn(movies);

        mockMvc.perform(get("/movie/get/{type}", type)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(movies.get(0).getName()))
                .andExpect(jsonPath("$[1].name").value(movies.get(1).getName()));
    }

    @Test
    @DisplayName("Get zero movies from a type")
    public void testGetMoviesByTypeBadFlow() throws Exception {
        String type = "CRIME";
        when(movieService.findMoviesByType(type)).thenThrow(new NotFoundMovieException("There is no movie from this type in the DB"));

        mockMvc.perform(get("/movie/get/{type}", type)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Add a Valid Movie")
    public void testAddValidMovie() throws Exception {
        Movie movie = new Movie(0, "Movie9", "CRIME", Date.valueOf("2014-03-12"), 6.4f);

        when(movieService.addMovie(any())).thenReturn(movie);

        mockMvc.perform(post("/movie/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(movie.getName()))
                .andExpect(jsonPath("$.grade").value(movie.getGrade()));
    }


    @Test
    @DisplayName("Try to add an invalid movie - grade is bigger than 10")
    public void testAddMovieWithInvalidGrade() throws Exception{
        Movie movie = new Movie(0, "Movie9", "CRIME", Date.valueOf("2014-03-12"), 15f);

        when(movieService.addMovie(any())).thenReturn(movie);

        MvcResult mvcResult = mockMvc.perform(post("/movie/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String expectedExceptionResponse = "For the filed called 'grade' , its value is not valid -> (15.0). The message sent with the validation is: must be less than or equal to 10";
        String actualExceptionBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualExceptionBody).isEqualTo(expectedExceptionResponse);
    }
}