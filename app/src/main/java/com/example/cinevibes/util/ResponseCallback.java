package com.example.cinevibes.util;

import com.example.cinevibes.model.Film;

import java.util.List;

public interface ResponseCallback {
    void onSuccess(List<Film> filmList, long lastUpdate);
    void onFailure(String errorMessage);
}
