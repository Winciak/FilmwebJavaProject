package com.example.FilmwebJavaProject.dao;


import com.example.FilmwebJavaProject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie findMovieByTitle(String title);
}
