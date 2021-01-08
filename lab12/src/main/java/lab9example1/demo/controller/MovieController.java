package lab9example1.demo.controller;

import lab9example1.demo.model.Movie;
import lab9example1.demo.service.MovieService;
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
    public ResponseEntity addMovie(@RequestBody @Valid Movie movie) {
        return ResponseEntity.created(null)
                .body(movieService.addMovie(movie));
    }

    @GetMapping("/get/{type}")
    public ResponseEntity getMoviesByType(@PathVariable("type") String type) {
        return ResponseEntity.ok(movieService.findMoviesByType(type));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity getMovieById(@PathVariable("id") int id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    @GetMapping("/get/after/{date}")
    public ResponseEntity getMoviesAfter(@PathVariable("date") String date) {
        return ResponseEntity.ok(movieService.findMoviesAfter(date));
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity removeMovieByTitle(@PathVariable("title") String title) {
        movieService.removeMovieByTitle(title);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateById(@Valid @RequestBody Movie movie) {
        movieService.updateById(movie);
        return ResponseEntity.ok().build();
    }

}
