package com.example.FilmwebJavaProject.dao;

import com.example.FilmwebJavaProject.entity.Filmmakers_movies;
import com.example.FilmwebJavaProject.entity.Ranking;
import com.example.FilmwebJavaProject.entity.Rankings_movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingMoviesRepository extends JpaRepository<Ranking,Integer> {

    List<Rankings_movies> findAllByRankingId(int id);

}
