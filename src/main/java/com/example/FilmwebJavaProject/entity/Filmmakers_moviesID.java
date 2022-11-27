package com.example.FilmwebJavaProject.entity;

import java.io.Serializable;
import java.util.Objects;

public class Filmmakers_moviesID implements Serializable {

    private int movie;

    private int filmmaker;

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public int getFilmmaker() {
        return filmmaker;
    }

    public void setFilmmaker(int filmmaker) {
        this.filmmaker = filmmaker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filmmakers_moviesID)) return false;
        Filmmakers_moviesID that = (Filmmakers_moviesID) o;
        return getMovie() == that.getMovie() && getFilmmaker() == that.getFilmmaker();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getFilmmaker());
    }

}
