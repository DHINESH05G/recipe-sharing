package com.dk.recipe_sharing.controller;

import com.dk.recipe_sharing.model.Recipe;
import com.dk.recipe_sharing.model.User;
import com.dk.recipe_sharing.service.RecipeService;
import com.dk.recipe_sharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private final RecipeService recipeService;
    @Autowired
    private final UserService userService;

    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
        User user=userService.findUserById(userId);
        recipe.setUser(user);
        Recipe createdRecipe=recipeService.createRecipe(recipe,user);
        return createdRecipe;
    }

    @GetMapping ()
    public List<Recipe> getAllRecipe() throws Exception {
        List<Recipe> recipes=recipeService.findAllRecipe();
        return recipes;
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return "Recipe Deleted Successfully";
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
        Recipe updatedRecipe=recipeService.updateRecipe(recipe,id);
        return updatedRecipe;
    }

    @PutMapping("/{id}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long id, @PathVariable Long userId) throws Exception {
        User user=userService.findUserById(userId);
        Recipe updatedRecipe=recipeService.likeRecipe(id,user);
        return updatedRecipe;
    }
}
