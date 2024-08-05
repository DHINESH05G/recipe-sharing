package com.dk.recipe_sharing.service;

import com.dk.recipe_sharing.model.Recipe;
import com.dk.recipe_sharing.model.User;
import com.dk.recipe_sharing.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class  RecipeServiceImplementation implements RecipeService{
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createRecipe=new Recipe();
        createRecipe.setTitle(recipe.getTitle());
        createRecipe.setImage(recipe.getImage());
        createRecipe.setDescription(recipe.getDescription());
        createRecipe.setUser(recipe.getUser());
        createRecipe.setCreatedAt(LocalDateTime.now());
        return recipeRepository.save(createRecipe);
    }

    @Override
    public Recipe findRecipeByid(Long id) throws Exception {
        Optional<Recipe> opt=recipeRepository.findById(id);
        if(opt.isPresent()) {return opt.get();}
       throw new Exception("Recipe is not found in your ID:"+id);
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
       findRecipeByid(id);
       recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe= findRecipeByid(id);
        if(recipe.getTitle()!=null){oldRecipe.setTitle(recipe.getTitle());}
        if(recipe.getImage()!=null){oldRecipe.setImage(recipe.getImage());}
        if(recipe.getDescription()!=null){oldRecipe.setDescription(recipe.getDescription());}
        return recipeRepository.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe=findRecipeByid(recipeId);
        if(recipe.getLikes().contains(user.getId())){recipe.getLikes().remove(user.getId());}
        else {recipe.getLikes().add(user.getId());}
        return recipeRepository.save(recipe);
    }
}
