package com.example.FilmwebJavaProject.dao;


import com.example.FilmwebJavaProject.entity.Rankings_movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingMoviesRepository extends JpaRepository<Rankings_movies,Integer> {

    List<Rankings_movies> findAllByRankingId(int id);

}
