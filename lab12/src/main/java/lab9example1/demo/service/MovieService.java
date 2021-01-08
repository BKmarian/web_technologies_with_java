package lab9example1.demo.service;

import lab9example1.demo.exception.NotFoundMovieException;
import lab9example1.demo.model.Movie;
import lab9example1.demo.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.saveMovie(movie);
    }

    public List<Movie> findMoviesAfter(String date) {
        List<Movie> movies = movieRepository.findMoviesAfter(date);
        if (movies.isEmpty()) {
            throw new NotFoundMovieException("There is no movie published after this date!");
        }
        return movies;
    }

    public List<Movie> findMoviesByType(String type) {
        List<Movie> movies = movieRepository.findMoviesByType(type);
        if (movies.isEmpty()) {
            throw new NotFoundMovieException("There is no movie from this type in the DB");
        }
        return movies;
    }

    public Movie findMovieById(int id) {
        Optional<Movie> movie = movieRepository.findMovieById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new NotFoundMovieException("There is no movie withe the requested id!");
        }
    }

    public void removeMovieByTitle(String title) {
        if (!movieRepository.deleteByTitle(title)) {
            throw new NotFoundMovieException("The movie with this title doesn't exist so it can't be deleted!");
        }
    }

    public void updateById(Movie movie) {
        if (!movieRepository.updateById(movie)) {
            throw new NotFoundMovieException("The movie with this id doesn't exist so it can't be updated!");
        }
    }

}
