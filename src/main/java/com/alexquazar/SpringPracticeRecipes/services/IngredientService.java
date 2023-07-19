package com.alexquazar.SpringPracticeRecipes.services;

import java.util.concurrent.ExecutionException;

import com.alexquazar.SpringPracticeRecipes.commands.IngredientCommand;

import reactor.core.publisher.Mono;

public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command) throws InterruptedException, ExecutionException;

    Mono<Void> deleteById(String recipeId, String String) throws InterruptedException, ExecutionException;
}
