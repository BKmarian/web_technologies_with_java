package com.example.lab4ex1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, path = "/home")
   // @RequestMapping(path = "/home")
   // @GetMapping(path = "/home")
    public String homePage()    {
        return "home.html";
    }
}
