package com.example.FilmwebJavaProject.service;

import com.example.FilmwebJavaProject.dao.GenreRepository;
import com.example.FilmwebJavaProject.entity.Filmmaker;
import com.example.FilmwebJavaProject.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findGenreByName(String name) {
        return genreRepository.findGenreByName(name);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(int id) {

        Optional<Genre> result = genreRepository.findById(id);

        Genre genre;

        if (result.isPresent()) {
            genre = result.get();
        }
        else {
            throw new RuntimeException("Did not find genre id - " + id);
        }

        return genre;
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void deleteById(int id) {
        genreRepository.deleteById(id);
    }
}
