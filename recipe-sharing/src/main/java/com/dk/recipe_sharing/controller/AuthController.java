package com.dk.recipe_sharing.controller;

import com.dk.recipe_sharing.config.JwtProvider;
import com.dk.recipe_sharing.repository.UserRepository;
import com.dk.recipe_sharing.service.CustomUserDetialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CustomUserDetialsService customUserDetialsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
}
