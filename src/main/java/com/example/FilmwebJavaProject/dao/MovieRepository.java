package com.example.FilmwebJavaProject.dao;


import com.example.FilmwebJavaProject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie findMovieByTitle(String title);

    List<Movie> findMoviesByTitleContaining(String search);

}
