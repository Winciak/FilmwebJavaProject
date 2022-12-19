package com.example.FilmwebJavaProject.controller;

import com.example.FilmwebJavaProject.entity.*;
import com.example.FilmwebJavaProject.service.FilmmakerService;
import com.example.FilmwebJavaProject.service.GenreService;
import com.example.FilmwebJavaProject.service.MovieService;
import com.example.FilmwebJavaProject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MovieController {


    private MovieService movieService;

    private UserService userService;

    private int FLAG_INDEX = -999;


    public MovieController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping("/movie")
    public String showMovieSiteAfterRating(@RequestParam("movieTitle")String movieTitle, @AuthenticationPrincipal UserDetails loggedUser,
                                           Model theModel){

        Movie movie = movieService.findMovieByTitle(movieTitle);

        List<Filmmakers_movies> filmmakers_movies = movie.getFilmmakers_movies();

        Filmmaker director = null;
        Filmmaker writer = null;

        int directorIndex = FLAG_INDEX;
        int writerIndex = FLAG_INDEX;

        for(int i=0;i<filmmakers_movies.size();i++){
            if (filmmakers_movies.get(i).getPosition().equals("Director")) {
                director = filmmakers_movies.get(i).getFilmmaker();
                directorIndex = i;
            }
            if (filmmakers_movies.get(i).getPosition().equals("Writer")) {
                writer = filmmakers_movies.get(i).getFilmmaker();
                writerIndex = i;
            }
        }

        if (directorIndex>writerIndex) {
            if(directorIndex!=FLAG_INDEX){
                filmmakers_movies.remove(directorIndex);
            }
            if(writerIndex!=FLAG_INDEX){
                filmmakers_movies.remove(writerIndex);
            }
        } else {
            if(writerIndex!=FLAG_INDEX){
                filmmakers_movies.remove(writerIndex);
            }
            if(directorIndex!=FLAG_INDEX){
                filmmakers_movies.remove(directorIndex);
            }
        }

        if(writer==null){
            writer = new Filmmaker("Writer", "Skywriter");
        }

        theModel.addAttribute("movie", movie);

        theModel.addAttribute("director", director);

        theModel.addAttribute("writer", writer);

        theModel.addAttribute("actors", filmmakers_movies);


        Review review = new Review();

        List<Review> reviewList = movie.getReviews();

        if (loggedUser!=null) {
            String login = loggedUser.getUsername();

            User user = userService.findByLogin(login);

            review = userService.findReviewByUserIdAndMovieId(user.getId(), movie.getId());
        }

        theModel.addAttribute("review", review);
        theModel.addAttribute("reviewList", reviewList);


        return "movieSite/movie-page-rated";
    }

    @GetMapping("/movieEdit")
    public String showMovieSite(@RequestParam("movieTitle")String movieTitle, @AuthenticationPrincipal UserDetails loggedUser,
                                Model theModel){

        Movie movie = movieService.findMovieByTitle(movieTitle);

        List<Filmmakers_movies> filmmakers_movies = movie.getFilmmakers_movies();

        Filmmaker director = null;
        Filmmaker writer = null;

        int directorIndex = FLAG_INDEX;
        int writerIndex = FLAG_INDEX;

        for(int i=0;i<filmmakers_movies.size();i++){
            if (filmmakers_movies.get(i).getPosition().equals("Director")) {
                director = filmmakers_movies.get(i).getFilmmaker();
                directorIndex = i;
            }
            if (filmmakers_movies.get(i).getPosition().equals("Writer")) {
                writer = filmmakers_movies.get(i).getFilmmaker();
                writerIndex = i;
            }
        }

        if (directorIndex>writerIndex) {
            if(directorIndex!=FLAG_INDEX){
                filmmakers_movies.remove(directorIndex);
            }
            if(writerIndex!=FLAG_INDEX){
                filmmakers_movies.remove(writerIndex);
            }
        } else {
            if(writerIndex!=FLAG_INDEX){
                filmmakers_movies.remove(writerIndex);
            }
            if(directorIndex!=FLAG_INDEX){
                filmmakers_movies.remove(directorIndex);
            }
        }

        if(writer==null){
            writer = new Filmmaker("Writer", "Skywriter");
        }

        theModel.addAttribute("movie", movie);

        theModel.addAttribute("director", director);

        theModel.addAttribute("writer", writer);

        theModel.addAttribute("actors", filmmakers_movies);


        Review review = new Review();

        List<Review> reviewList = movie.getReviews();

        if (loggedUser!=null) {
            String login = loggedUser.getUsername();
            User user = userService.findByLogin(login);

            if(userService.findReviewByUserIdAndMovieId(user.getId(), movie.getId())==null) {
                review = new Review();
                review.setMovie(movie);
                review.setUser(user);
            }
            else {
                review = userService.findReviewByUserIdAndMovieId(user.getId(), movie.getId());
            }
        }


        theModel.addAttribute("review", review);

        theModel.addAttribute("reviewList", reviewList);


        return "movieSite/movie-page";
    }


    @PostMapping("/movie")
    public String saveRating(@RequestParam("movieTitle")String movieTitle,
                             @Valid @ModelAttribute("review") Review review){

        Movie movie = movieService.findMovieByTitle(movieTitle);

        //save Review
        userService.saveReview(review);


        //update overall movie rating
        List<Review> reviewList = movie.getReviews();
        float sum = 0;

        for (Review value : reviewList) {
            sum += value.getRating();
        }

        sum=sum/reviewList.size();

        movie.setMovie_rating(sum);

        movieService.save(movie);

        return "redirect:/movie/?movieTitle="+movie.getTitle();
    }



}
