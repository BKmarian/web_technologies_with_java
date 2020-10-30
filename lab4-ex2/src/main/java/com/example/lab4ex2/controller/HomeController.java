package com.example.lab4ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

//    @GetMapping("/home")
//    public String homePage()    {
//        return "home.html";
//    }

    @GetMapping("/home/{username}")
    public String homePage(@PathVariable String username)    {
        System.out.println("Username is " + username);
        return "home.html";
    }

//    @GetMapping("/parameters")
//    public String paramPage(@RequestParam String name, Model model){
//        model.addAttribute("name", name);
//        return "parameters";
//    }

    @GetMapping("/parameters")
    public String paramPage(@RequestParam String name,
                            @RequestParam String username,
                            Model model){
        model.addAttribute("name", name);
        model.addAttribute("username", username);
        return "parameters";
    }
}
