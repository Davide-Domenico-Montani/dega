package com.example.cinevibes.model;

import java.util.List;

public class FilmApiResponse extends FilmResponse {

    private int page;
    private List<Film> results;
    private int total_pages;
    private int total_results;

    public FilmApiResponse() {
        super();
    }

    public FilmApiResponse(String status, int total_results, List<Film> films) {
        super(films);
        this.total_results = total_results;
        this.page = 1;
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }

    public List<Film> getResults() {

        return results;
    }

    public void setResults(List<Film> results) {

        this.results = results;
    }

    public int getTotal_pages() {

        return total_pages;
    }

    public void setTotal_pages(int total_pages) {

        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
