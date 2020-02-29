package com.miniq.film.resolver;

import com.miniq.example.graphql.dto.Film;
import com.miniq.film.service.FilmService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Uladik
 */
@Component
public class MutationResolver {

    private FilmService filmService;

    @Autowired
    public MutationResolver(FilmService filmService) {
        this.filmService = filmService;
    }

    public DataFetcher<Long> putFilm() {
        return dataFetchingEnvironment -> {
            Film data = convertInputData(dataFetchingEnvironment.getArgument("name"));
            return filmService.put(data);
        };
    }

    private Film convertInputData(String name) {
        Film dto = new Film();
        dto.setName(name);
        return dto;
    }
}
