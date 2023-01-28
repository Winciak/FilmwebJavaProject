package com.example.FilmwebJavaProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "rankings_movies")
@IdClass(Rankings_moviesID.class)
public class Rankings_movies {


    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id_movie")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "ranking_Id", referencedColumnName = "id_ranking")
    private Ranking ranking;


    @Column(name = "position")
    private int position;

    public Rankings_movies() {
    }

    public Rankings_movies(Movie movie, Ranking ranking, int position) {
        this.movie = movie;
        this.ranking = ranking;
        this.position = position;
    }
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return null;
    }
}