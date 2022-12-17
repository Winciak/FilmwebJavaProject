package com.example.FilmwebJavaProject.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie", nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private Date release_date;

    @Column(name = "movie_rating")
    private float movie_rating;

    @OneToMany(mappedBy = "movie",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "movies_images",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Collection<ImageEntity> images;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "genres_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Collection<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "rankings_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "ranking_id"))
    private Collection<Ranking> rankings;

    @OneToMany(mappedBy = "movie")
    private List<Filmmakers_movies> filmmakers_movies;

    public Movie() {
    }

    public Movie(int id, String title, String description, Date release_date, float movie_rating, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.movie_rating = movie_rating;
        this.reviews = reviews;
    }

    public Movie(int id, String title, String description, Date release_date, float movie_rating, List<Review> reviews, Collection<ImageEntity> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.movie_rating = movie_rating;
        this.reviews = reviews;
        this.images = images;
    }

    public Movie(int id, String title, String description, Date release_date, float movie_rating, List<Review> reviews, Collection<ImageEntity> images, Collection<Genre> genres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.movie_rating = movie_rating;
        this.reviews = reviews;
        this.images = images;
        this.genres = genres;
    }

    public Movie(int id, String title, String description, Date release_date, float movie_rating, List<Review> reviews, Collection<ImageEntity> images, Collection<Genre> genres, Collection<Ranking> rankings) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.movie_rating = movie_rating;
        this.reviews = reviews;
        this.images = images;
        this.genres = genres;
        this.rankings = rankings;
    }

    public Movie(int id, String title, String description, Date release_date, float movie_rating, List<Review> reviews, Collection<ImageEntity> images, Collection<Genre> genres, Collection<Ranking> rankings, List<Filmmakers_movies> filmmakers_movies) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.movie_rating = movie_rating;
        this.reviews = reviews;
        this.images = images;
        this.genres = genres;
        this.rankings = rankings;
        this.filmmakers_movies = filmmakers_movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public float getMovie_rating() {
        return movie_rating;
    }

    public void setMovie_rating(float movie_rating) {
        this.movie_rating = movie_rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Collection<ImageEntity> getImages() {
        return images;
    }

    public void setImages(Collection<ImageEntity> images) {
        this.images = images;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
    }

    public Collection<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(Collection<Ranking> rankings) {
        this.rankings = rankings;
    }

    public List<Filmmakers_movies> getFilmmakers_movies() {
        return filmmakers_movies;
    }

    public void setFilmmakers_movies(List<Filmmakers_movies> filmmakers_movies) {
        this.filmmakers_movies = filmmakers_movies;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_date=" + release_date +
                ", movie_rating=" + movie_rating +
                ", reviews=" + reviews +
                ", images=" + images +
                ", genres=" + genres +
                ", rankings=" + rankings +
                ", filmmakers_movies=" + filmmakers_movies +
                '}';
    }
}