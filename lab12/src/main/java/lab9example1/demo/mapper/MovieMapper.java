package lab9example1.demo.mapper;

import lab9example1.demo.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setName(resultSet.getString("name"));
        movie.setType(resultSet.getString("type"));
        movie.setDate(resultSet.getDate("date"));
        movie.setGrade(resultSet.getFloat("grade"));

        return movie;
    }
}
