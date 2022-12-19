package com.example.FilmwebJavaProject.controller;

import com.example.FilmwebJavaProject.entity.Genre;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("search")
    public Genre populateUser() {
        return new Genre();

    }
}
