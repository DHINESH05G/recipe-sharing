package com.dk.recipe_sharing.repository;

import com.dk.recipe_sharing.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
}
