package com.example.graphql.film.mapper;

import com.example.graphql.dto.Film;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Uladik
 */
@Component
public class FilmRowMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet resultSet, int i) throws SQLException {
        Film result = new Film();
        result.setId(resultSet.getLong("id"));
        result.setName(resultSet.getString("name"));
        return result;
    }

}
