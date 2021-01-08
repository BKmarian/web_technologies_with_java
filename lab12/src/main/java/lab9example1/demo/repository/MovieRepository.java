package lab9example1.demo.repository;

import lab9example1.demo.mapper.MovieMapper;
import lab9example1.demo.model.Movie;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Movie saveMovie(Movie movie) {
        String insertSql = "INSERT INTO movie VALUES(NULL, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSql, movie.getName(), movie.getType(), movie.getDate(), movie.getGrade());
        return movie;
    }

    public List<Movie> findMoviesAfter(String date) {
        String selectSql = "SELECT * FROM movie WHERE year(date) >= ?";
        return jdbcTemplate.query(selectSql, new MovieMapper(), date);
    }

    public List<Movie> findMoviesByType(String type) {
        String selectSql = "SELECT * FROM movie WHERE type LIKE ?";
        return jdbcTemplate.query(selectSql, new MovieMapper(), type);
    }

    public Optional<Movie> findMovieById(int id) {
        String selectSql = "SELECT * FROM movie WHERE id = ?";
        try {
            Movie movie = jdbcTemplate.queryForObject(selectSql, new MovieMapper(), id);
            return Optional.of(movie);
        } catch (EmptyResultDataAccessException e)  {
            return Optional.empty();
        }
    }

    public boolean deleteByTitle(String tile) {
        String deleteSql = "DELETE FROM movie WHERE name = ?";
        return jdbcTemplate.update(deleteSql, tile) != 0;
    }

    public boolean updateById(Movie movie) {
        String updateSql = "UPDATE movie set name = ?, type = ?, date = ?, grade = ? WHERE id = ?";
        return jdbcTemplate.update(updateSql,
                movie.getName(),
                movie.getType(),
                movie.getDate(),
                movie.getGrade(),
                movie.getId()) != 0;
    }
}
