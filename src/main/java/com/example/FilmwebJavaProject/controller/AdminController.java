package com.example.FilmwebJavaProject.controller;


import com.example.FilmwebJavaProject.entity.Role;
import com.example.FilmwebJavaProject.entity.User;
import com.example.FilmwebJavaProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
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

}
