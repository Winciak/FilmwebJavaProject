package com.example.FilmwebJavaProject.entity;

import javax.persistence.*;


@Entity
@Table(name = "user_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_review")
    private int id;

    @Column(name = "rating")
    private float rating;

    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "movie_ID")
    private Movie movie;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Review() {
    }

    public Review(int id, int rating, String text, Movie movie, User user) {
        this.id = id;
        this.rating = rating;
        this.text = text;
        this.movie = movie;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return null;
    }
}
