package com.example.FilmwebJavaProject.entity;

import java.io.Serializable;
import java.util.Objects;

public class Rankings_moviesID implements Serializable {

    private int movie;

    private int ranking;

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rankings_moviesID)) return false;
        Rankings_moviesID that = (Rankings_moviesID) o;
        return getMovie() == that.getMovie() && getRanking() == that.getRanking();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getRanking());
    }

}
