package com.example.FilmwebJavaProject.controller;


import com.example.FilmwebJavaProject.entity.Filmmaker;
import com.example.FilmwebJavaProject.entity.Role;
import com.example.FilmwebJavaProject.entity.User;
import com.example.FilmwebJavaProject.service.FilmmakerService;
import com.example.FilmwebJavaProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private FilmmakerService filmmakerService;

    public AdminController(UserService userService, FilmmakerService filmmakerService) {
        this.userService = userService;
        this.filmmakerService = filmmakerService;
    }

    @GetMapping("/panel")
    public String adminPanel(){


        return "admin/admin-panel";

    }

    @GetMapping("/list")
    public String listUsers(Model theModel){

        List<User> userList = userService.findAll();

        theModel.addAttribute("users", userList);

        return "admin/list-users";

    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){

        userService.save(user);

        return "redirect:/admin/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUserFormForUpdate(@RequestParam("userId")int theId, Model theModel){

        User user = userService.findById(theId);

        List<Role> roleList = userService.findRoles();

        theModel.addAttribute("user", user);

        theModel.addAttribute("listRoles", roleList);

        return "admin/roles-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId){

        userService.deleteById(theId);

        return "redirect:/admin/list";
    }

    //-----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------FILMMAKER------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    @GetMapping("/filmmakers/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Filmmaker filmmaker = new Filmmaker();


        theModel.addAttribute("filmmaker", filmmaker);


        return "/filmmaker/add-form";
    }

    @GetMapping("/filmmakers/showFormForUpdate")
    public String showFilmmakerFormForUpdate(@RequestParam("filmmakerId")int id, Model theModel){

        Filmmaker filmmaker = filmmakerService.findById(id);


        theModel.addAttribute("filmmaker", filmmaker);


        return "/filmmaker/add-form";
    }


    @PostMapping("/saveFilmmaker")
    public String saveFilmmaker(@Valid @ModelAttribute("filmmaker") Filmmaker filmmaker,
                                BindingResult theBindingResult){


        if (theBindingResult.hasErrors()){

            return "/filmmaker/add-form";
        }



        filmmakerService.save(filmmaker);


        return "redirect:/admin/panel";
    }

    @GetMapping("/filmmakers/list")
    public String listFilmmakers(Model theModel){

        List<Filmmaker> filmmakerList = filmmakerService.findAll();

        theModel.addAttribute("filmmakers", filmmakerList);

        return "/filmmaker/list";

    }

    @GetMapping("/filmmakers/delete")
    public String deleteFilmmaker(@RequestParam("filmmakerId") int id){

        filmmakerService.deleteById(id);

        return "redirect:/admin/filmmakers/list";
    }

}
