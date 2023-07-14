package com.alexquazar.SpringPracticeRecipes.services;

import com.alexquazar.SpringPracticeRecipes.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(String recipeId, String String);
}
