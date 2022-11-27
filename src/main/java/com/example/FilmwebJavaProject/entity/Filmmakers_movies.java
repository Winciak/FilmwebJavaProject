package com.example.FilmwebJavaProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "filmmakers_movies")
@IdClass(Filmmakers_moviesID.class)
public class Filmmakers_movies {

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id_movie")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "filmmaker_id", referencedColumnName = "id_filmmaker")
    private Filmmaker filmmaker;

    @Column(name = "position")
    private String position;

    @Column(name = "role")
    private String role;

    public Filmmakers_movies() {
    }

    public Filmmakers_movies(Movie movie, Filmmaker filmmaker, String position, String role) {
        this.movie = movie;
        this.filmmaker = filmmaker;
        this.position = position;
        this.role = role;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Filmmaker getFilmmaker() {
        return filmmaker;
    }

    public void setFilmmaker(Filmmaker filmmaker) {
        this.filmmaker = filmmaker;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Filmmakers_movies{" +
                "movie=" + movie +
                ", filmmaker=" + filmmaker +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}