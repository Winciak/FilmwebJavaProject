package com.example.FilmwebJavaProject.dao;


import com.example.FilmwebJavaProject.entity.Filmmakers_movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmmakerMoviesRepository extends JpaRepository<Filmmakers_movies,Integer> {

    List<Filmmakers_movies> findAllByMovieId(int id);


}
