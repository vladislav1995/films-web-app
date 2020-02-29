package com.miniq.film.resolver;

import com.miniq.example.graphql.dto.Film;
import com.miniq.film.service.FilmService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Uladik
 */
@Component
public class QueryResolver {

    private FilmService filmService;

    @Autowired
    public QueryResolver(FilmService filmService) {
        this.filmService = filmService;
    }

    public DataFetcher<Film> getFilmById() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return filmService.get(Long.parseLong(id));
        };
    }

    public DataFetcher<List<Film>> getFilmsByName() {
        return dataFetchingEnvironment -> {
            String method = dataFetchingEnvironment.getArgument("name");
            return filmService.getByMethod(method);
        };
    }

}
