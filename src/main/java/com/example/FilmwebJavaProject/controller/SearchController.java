package com.example.FilmwebJavaProject.controller;

import com.example.FilmwebJavaProject.entity.Movie;
import com.example.FilmwebJavaProject.service.MovieService;
import com.example.FilmwebJavaProject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {


    private MovieService movieService;



    public SearchController(MovieService movieService) {

        this.movieService = movieService;

    }


    @GetMapping("/search")
    public String showMovieSiteAfterRating(@RequestParam(value = "name", required = false)String searchTitle, Model theModel){

        List<Movie> movieList = movieService.findMoviesByTitleContaining(searchTitle);

        theModel.addAttribute("movieList", movieList);

        if(searchTitle==null) searchTitle="all movies";

        theModel.addAttribute("searchText", searchTitle);

        return "movieSite/search-page";
    }
}
