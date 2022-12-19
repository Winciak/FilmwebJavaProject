package com.example.FilmwebJavaProject.service;

import com.example.FilmwebJavaProject.entity.Filmmakers_movies;
import com.example.FilmwebJavaProject.entity.Genre;
import com.example.FilmwebJavaProject.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie findMovieByTitle(String title);

    List<Filmmakers_movies> findAllFilmmakersByMovieId(int id);

    List<Movie> findAll();

    Movie findById(int id);

    void save(Movie movie);

    void saveFilmmakersMovies(Filmmakers_movies filmmakers_movies);

    void deleteById(int id);

    List<Movie> findMoviesByTitleContaining(String search);

}
