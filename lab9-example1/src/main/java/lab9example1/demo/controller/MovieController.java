package lab9example1.demo.controller;

import lab9example1.demo.model.Movie;
import lab9example1.demo.service.MovieService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public ResponseEntity addMovie(@Valid @RequestBody Movie movie)   {
        movieService.addMovie(movie);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/get/after/{date}")
    public ResponseEntity getMoviesAfter(@PathVariable("date") String date) {
//        try {
//            return ResponseEntity.ok(movieService.findMoviesAfter(date));
//        } catch (NoMovieFoundException e)   {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(movieService.findMoviesAfter(date));
    }

    @GetMapping("/get/{type}")
    public ResponseEntity getMoviesByType(@PathVariable("type") String type)    {
        return ResponseEntity.ok(movieService.findMoviesByType(type));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity getMoviesByType(@PathVariable("id") int id)    {
        try {
            return ResponseEntity.ok(movieService.findMovieById(id));
        } catch (EmptyResultDataAccessException e)  {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity removeMovieByTitle(@PathVariable("title") String title) {
        movieService.removeMovieByTitle(title);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateById(@Valid @RequestBody Movie movie)   {
        movieService.updateById(movie);
        return ResponseEntity.ok().build();
    }

}
