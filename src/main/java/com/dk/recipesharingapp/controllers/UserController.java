package com.dk.recipesharingapp.controllers;

import com.dk.recipesharingapp.models.User;
import com.dk.recipesharingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
//        System.out.println("inside co");
        User user=userService.findUserByJwt(jwt);

        return user;

    }

//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/users")
//    public User createUser(@RequestBody User user) throws Exception {
//
//        User existingUser=userRepository.findByEmail(user.getEmail());
//
//        if(existingUser!=null){
//            throw new Exception("user already exist with given email");
//        }
//        User savedUser=userRepository.save(user);
//        return savedUser;
//    }
//
//
//    @DeleteMapping("/users/{userId}")
//    public String deleteUser(@PathVariable Long userId) throws Exception {
//        userRepository.deleteById(userId);
//
//        return "user deleted Successfully";
//    }
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() throws Exception {
//        List<User> users=userRepository.findAll();
//        return users;
//    }


//    public User findByEmail(String email) throws Exception {
//        User user=userRepository.findByEmail(email);
//
//        if(user==null){
//            throw new Exception("user not found with given email");
//        }
//        return user;
//    }
}
