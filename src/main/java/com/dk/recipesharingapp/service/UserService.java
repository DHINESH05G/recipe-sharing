package com.dk.recipesharingapp.service;

import com.dk.recipesharingapp.models.User;

public interface UserService {

    public User findUserById(Long userId) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;


}
