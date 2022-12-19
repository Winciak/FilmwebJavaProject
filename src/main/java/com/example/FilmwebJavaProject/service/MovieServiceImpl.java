package com.example.FilmwebJavaProject.service;

import com.example.FilmwebJavaProject.dao.FilmmakerMoviesRepository;
import com.example.FilmwebJavaProject.dao.MovieRepository;
import com.example.FilmwebJavaProject.entity.Filmmakers_movies;
import com.example.FilmwebJavaProject.entity.Genre;
import com.example.FilmwebJavaProject.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    FilmmakerMoviesRepository filmmakerMoviesRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, FilmmakerMoviesRepository filmmakerMoviesRepository) {
        this.movieRepository = movieRepository;
        this.filmmakerMoviesRepository = filmmakerMoviesRepository;
    }

    @Override
    public Movie findMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    @Override
    public List<Filmmakers_movies> findAllFilmmakersByMovieId(int id) {
        return filmmakerMoviesRepository.findAllByMovieId(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int id) {

        Optional<Movie> result = movieRepository.findById(id);

        Movie movie;

        if (result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new RuntimeException("Did not find movie id - " + id);
        }

        return movie;
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void saveFilmmakersMovies(Filmmakers_movies filmmakers_movies) {
        filmmakerMoviesRepository.save(filmmakers_movies);
    }

    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findMoviesByTitleContaining(String search) {
        return movieRepository.findMoviesByTitleContaining(search);
    }
}
