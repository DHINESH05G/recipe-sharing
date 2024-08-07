package com.dk.recipe_sharing.service;

import com.dk.recipe_sharing.model.User;
import com.dk.recipe_sharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt=userRepository.findById(userId);
        if(opt.isPresent()) return opt.get();
        throw new Exception("User not found with Id:"+userId);
    }
}
