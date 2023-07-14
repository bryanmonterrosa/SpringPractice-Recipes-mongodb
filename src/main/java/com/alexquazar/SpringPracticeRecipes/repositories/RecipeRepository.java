package com.alexquazar.SpringPracticeRecipes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alexquazar.SpringPracticeRecipes.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, String>{
    
}
