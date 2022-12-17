package com.example.FilmwebJavaProject.dao;

import com.example.FilmwebJavaProject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {

    Genre findGenreByName(String name);
}
