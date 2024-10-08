package com.dk.recipesharingapp.controllers;


import com.dk.recipesharingapp.service.RecipeService;
import com.dk.recipesharingapp.models.Recipe;
import com.dk.recipesharingapp.models.User;
import com.dk.recipesharingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

//    @PostMapping("/user/{userId}")
//    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
//
//        User user=userService.findUserById(userId);
//        Recipe createdRecipe=recipeService.createRecipe(recipe,user);
//        return createdRecipe;
//
//    }

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwt(jwt);
        Recipe createdRecipe=recipeService.createRecipe(recipe,user);
        return createdRecipe;

    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {

        Recipe updatedRecipe=recipeService.updateRecipe(recipe,id);
        return updatedRecipe;

    }

    @GetMapping()
    public List<Recipe> getAllRecipe() throws Exception {

        List<Recipe> allRecipe=recipeService.findAllRecipe();
        return allRecipe;

    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {

        recipeService.deleteRecipe(recipeId);
        return "recipe deleted successfully";
    }

//    @PutMapping("/{id}/like/user/{userId}")
//    public Recipe likeRecipe(@PathVariable Long id, @PathVariable Long userId) throws Exception {
//
//        User user=userService.findUserById(userId);
//        Recipe updatedRecipe=recipeService.likeRecipe(id,user);
//        return updatedRecipe;
//
//    }

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwt(jwt);
        Recipe updatedRecipe=recipeService.likeRecipe(id,user);
        return updatedRecipe;

    }
}
