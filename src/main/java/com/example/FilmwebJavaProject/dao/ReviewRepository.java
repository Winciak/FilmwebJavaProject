package com.example.FilmwebJavaProject.dao;

import com.example.FilmwebJavaProject.entity.Movie;
import com.example.FilmwebJavaProject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Review findReviewByUserIdAndMovieId(int userId, int movieId);

}
