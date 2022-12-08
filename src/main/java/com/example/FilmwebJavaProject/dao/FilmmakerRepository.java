package com.example.FilmwebJavaProject.dao;

import com.example.FilmwebJavaProject.entity.Filmmaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmmakerRepository extends JpaRepository<Filmmaker,Integer> {




}
