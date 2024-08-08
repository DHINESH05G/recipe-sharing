package com.dk.recipe_sharing.controller;

import com.dk.recipe_sharing.config.JwtProvider;
import com.dk.recipe_sharing.model.User;
import com.dk.recipe_sharing.repository.UserRepository;
import com.dk.recipe_sharing.respose.AuthRespose;
import com.dk.recipe_sharing.service.CustomUserDetialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

   public AuthRespose createUser(@RequestBody User user) throws Exception{
       String email= user.getEmail();
       String password= user.getPassword();
       String fullName= user.getFullname();
       User isExistEmail=userRepository.findByEmail(email);
       if(isExistEmail!=null)
       {throw new Exception("Email Already Exist");}
       User createdUser=new User();
       createdUser.setEmail(email);
       createdUser.setPassword(passwordEncoder.encode(password));
       createdUser.setFullname(fullName);


       User savedUser = userRepository.save(createdUser);
       Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token = jwtProvider.generateToken(authentication);

       return null;
   }
}
