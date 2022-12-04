package com.example.FilmwebJavaProject.controller;

import com.example.FilmwebJavaProject.dto.CustomUserDetails;
import com.example.FilmwebJavaProject.dto.DtoUser;
import com.example.FilmwebJavaProject.entity.User;
import com.example.FilmwebJavaProject.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class AccountController {

    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String viewAccount(){



        return "users/account-page";
    }

    @GetMapping("/account/edit")
    public String viewAccountForEdit(@AuthenticationPrincipal UserDetails loggedUser, Model theModel){

        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);
        DtoUser dtoUser = new DtoUser();
        dtoUser.setLogin(user.getLogin());
        dtoUser.setFirstName(user.getFirstName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setAbout(user.getAbout());


        theModel.addAttribute("dtoUser",dtoUser);


        return "users/account-page-edit";
    }

    @PostMapping("/account/edit/update")
    public String processRegistrationForm(
            @AuthenticationPrincipal CustomUserDetails loggedUser,
            @Valid @ModelAttribute("dtoUser") DtoUser dtoUser,
            BindingResult theBindingResult,
            Model theModel) {

        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);

        String dtoUserLogin = dtoUser.getLogin();

        System.out.println("pass: " + dtoUser.getPassword());


        // validation
        if (theBindingResult.hasErrors()){
            if(dtoUser.getPassword().isEmpty()){
                theModel.addAttribute("passwordError","Enter your password or new password");
            }
            return "users/account-page-edit";
        }

        // checking if user changed his login
        if (!Objects.equals(login, dtoUserLogin)) {
            // checking if user already exists
            User existing = userService.findByLogin(dtoUserLogin);
            if (existing != null ){
                theModel.addAttribute("editError", "Login already exists.");

                return "users/account-page-edit";
            }
        }

        loggedUser.setUsername(dtoUserLogin);
        loggedUser.setFirstName(dtoUser.getFirstName());
        loggedUser.setLastName(dtoUser.getLastName());
        loggedUser.setAbout(dtoUser.getAbout());



        userService.update(dtoUser,user);





        return "users/account-page";
    }
}
