package com.alexquazar.SpringPracticeRecipes.controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.alexquazar.SpringPracticeRecipes.config.WebConfig;
import com.alexquazar.SpringPracticeRecipes.model.Recipe;
import com.alexquazar.SpringPracticeRecipes.services.RecipeService;

import reactor.core.publisher.Flux;

public class RouterFunctionTest {
    WebTestClient webTestClient;

    @Mock
    RecipeService recipeService;
    
    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        WebConfig webConfig = new WebConfig();

        RouterFunction<?> routerFunction = webConfig.routes(recipeService);

        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    void testGetRecipes() throws Exception{

        when(recipeService.getRecipes()).thenReturn(Flux.just());

        webTestClient.get().uri("/api/recipes")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk();
    }

    @Test
    void testGetRecipesWithData() throws Exception{

        when(recipeService.getRecipes()).thenReturn(Flux.just(new Recipe(), new Recipe()));

        webTestClient.get().uri("/api/recipes")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Recipe.class);
    }

       
}
