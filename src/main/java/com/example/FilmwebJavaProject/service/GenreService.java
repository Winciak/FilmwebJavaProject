package com.example.FilmwebJavaProject.service;


import com.example.FilmwebJavaProject.entity.Genre;

import java.util.List;


public interface GenreService {

    Genre findGenreByName(String name);

    List<Genre> findAll();

    Genre findById(int id);

    void save(Genre genre);

    void deleteById(int id);
}
