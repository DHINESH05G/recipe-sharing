package com.dk.recipe_sharing.controller;

import com.dk.recipe_sharing.model.User;
import com.dk.recipe_sharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception{
        User isExist=userRepository.findByEmail(user.getEmail());
        if(isExist!=null){
            throw new Exception("user not found with email"+user.getEmail());
        }
        User savedUser=userRepository.save(user);
        return savedUser;
    }
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception{
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }

    @GetMapping("/users")
    public List<User> getallUsers () throws Exception{
        List<User> userList= userRepository.findAll();
        return userList;
    }
    /*public User findByEmail(String email) throws Exception{
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found with email"+email);
        }
    }*/

}
