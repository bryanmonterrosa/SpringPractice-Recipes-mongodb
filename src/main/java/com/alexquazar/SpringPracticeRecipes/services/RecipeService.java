package com.alexquazar.SpringPracticeRecipes.services;

import java.util.Set;

import com.alexquazar.SpringPracticeRecipes.commands.RecipeCommand;
import com.alexquazar.SpringPracticeRecipes.model.Recipe;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}