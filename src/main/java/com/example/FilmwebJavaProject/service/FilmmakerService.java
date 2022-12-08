package com.example.FilmwebJavaProject.service;

import com.example.FilmwebJavaProject.entity.Filmmaker;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FilmmakerService {

    List<Filmmaker> findAll();

    Filmmaker findById(int id);

    void save(Filmmaker filmmaker);

    void deleteById(int theId);
}
