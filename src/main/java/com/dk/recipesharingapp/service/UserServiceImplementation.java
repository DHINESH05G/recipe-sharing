package com.dk.recipesharingapp.service;

import com.dk.recipesharingapp.config.JwtProvider;
import com.dk.recipesharingapp.repository.UserRepository;
import com.dk.recipesharingapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt=userRepository.findById(userId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("user with given id doesn't exist");
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email= jwtProvider.getEmailFromJwtToken(jwt);
        if(email==null){
            throw new Exception("Provide valid jwt token");
        }
//        System.out.println("emailp"+email);
        User user=userRepository.findByEmail(email);
//        System.out.println("user"+user);

        if(user==null){
            throw new Exception("user not found with email"+email);
        }
        return user;

    }

}
