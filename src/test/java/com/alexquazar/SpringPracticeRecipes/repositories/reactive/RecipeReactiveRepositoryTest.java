package com.alexquazar.SpringPracticeRecipes.repositories.reactive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.alexquazar.SpringPracticeRecipes.model.Recipe;

@DataMongoTest
public class RecipeReactiveRepositoryTest {
    
    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @BeforeEach
    public void setUp() {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testRecipeSave() {
        Recipe recipe = new Recipe();
        recipe.setDescription("Yummy");

        recipeReactiveRepository.save(recipe).block();

        Long count = recipeReactiveRepository.count().block();

        assertEquals(Long.valueOf(1l), count);
    }
}
