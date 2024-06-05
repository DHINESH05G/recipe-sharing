package com.dk.recipe_sharing.service;

import com.dk.recipe_sharing.model.User;

public interface UserService {

    public User findUserById(Long userId)throws Exception;

}
