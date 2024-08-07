package com.dk.recipe_sharing.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @GetMapping
    public String homeController(){
        return "Welcome Back to MR.Dk";
    }
   // @PostMapping
   // @PutMapping
   // @DeleteMapping
}
