package com.miniq.film.service;

import com.miniq.example.graphql.dto.Film;
import com.miniq.example.graphql.repository.CrudRepository;
import com.miniq.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Uladik
 */
@Service
public class FilmService implements CrudRepository<Film, Long> {

    private FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film get(Long id) {
        return filmRepository.get(id);
    }

    public List<Film> getByMethod(String method) {
        return filmRepository.getByName(method);
    }

    @Override
    public Long put(Film data) {
        return filmRepository.put(data);
    }

    @Override
    public Long update(Film data) {
        return filmRepository.update(data);
    }

    @Override
    public boolean delete(Long id) {
        return filmRepository.delete(id);
    }
}
