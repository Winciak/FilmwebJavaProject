package com.example.FilmwebJavaProject.controller;

import com.example.FilmwebJavaProject.entity.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = {"/home","/"})
    public String showLanding() {



        return "index";

    }

    @GetMapping("/loginPage")
    public String showMyLoginPage() {

        return "login-page";

    }


    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }

}