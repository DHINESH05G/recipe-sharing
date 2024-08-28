package com.dk.recipesharingapp.repository;

import com.dk.recipesharingapp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepository extends JpaRepository<Recipe,Long> {
	
}
