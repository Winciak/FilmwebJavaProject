package com.example.FilmwebJavaProject.service;


import com.example.FilmwebJavaProject.entity.Genre;
import com.example.FilmwebJavaProject.entity.Ranking;
import com.example.FilmwebJavaProject.entity.Rankings_movies;

import java.util.List;


public interface RankingService {

    List<Rankings_movies> findAllByRankingId(int id);
    Ranking findRankingByName(String name);

    List<Ranking> findAll();

    Ranking findById(int id);

    void save(Ranking ranking);

    void deleteById(int id);
}
