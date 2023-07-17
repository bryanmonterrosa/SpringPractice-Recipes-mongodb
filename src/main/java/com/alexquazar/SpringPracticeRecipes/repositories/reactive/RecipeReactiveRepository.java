package com.alexquazar.SpringPracticeRecipes.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.alexquazar.SpringPracticeRecipes.model.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {

}
