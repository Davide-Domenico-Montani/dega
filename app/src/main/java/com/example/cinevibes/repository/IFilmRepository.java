package com.example.cinevibes.repository;

import java.util.List;

public interface IFilmRepository {
    void fetchFilms (List<Integer> genre_ids, int page, long lastUpdate);

    void getListFilms();
}
