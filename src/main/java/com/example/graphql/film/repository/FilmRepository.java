package com.example.graphql.film.repository;

import com.google.common.collect.ImmutableList;
import com.example.graphql.dto.Film;
import com.example.graphql.provider.JdbcTemplateProvider;
import com.example.graphql.repository.CrudRepository;
import com.example.graphql.film.mapper.FilmRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Uladik
 */
@Repository
public class FilmRepository implements CrudRepository<Film, Long> {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM apollo_federation.film WHERE film.id = ?";
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM apollo_federation.film WHERE film.name = ?";
    private static final String INSERT_QUERY = "INSERT INTO apollo_federation.film (name) VALUES (?)";

    private JdbcTemplate template;
    private FilmRowMapper filmRowMapper;

    @Autowired
    public FilmRepository(JdbcTemplateProvider provider, FilmRowMapper filmRowMapper) {
        this.template = provider.getTemplate();
        this.filmRowMapper = filmRowMapper;
    }

    @Override
    public Film get(Long id) {
        return template.queryForObject(SELECT_BY_ID_QUERY, filmRowMapper, id);
    }

    public List<Film> getByName(String name) {
        return template.query(SELECT_BY_NAME_QUERY, filmRowMapper, name);
    }

    @Override
    public Long put(Film data) {
        return (long) template.update(INSERT_QUERY, convertData(data));
    }

    @Override
    public Long update(Film data) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    private Object[] convertData(Film data) {
        return ImmutableList.of(data.getName()).toArray();
    }
}
