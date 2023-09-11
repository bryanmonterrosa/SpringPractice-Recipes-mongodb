package com.alexquazar.SpringPracticeRecipes.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.alexquazar.SpringPracticeRecipes.model.Recipe;
import com.alexquazar.SpringPracticeRecipes.services.RecipeService;

import reactor.core.publisher.Flux;

@Disabled
public class IndexControllerTest {
    // @Mock
    // RecipeService recipeService;

    // @Mock
    // Model model;

    // IndexController controller;

    // @BeforeEach
    // void setUp() {
    //     MockitoAnnotations.openMocks(this);
    //     controller = new IndexController(recipeService);
    // }

    // @Test
    // void testMockMVC() throws Exception {
    //     MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
            
    //     when(recipeService.getRecipes()).thenReturn(Flux.empty());

    //     mockMvc.perform(MockMvcRequestBuilders.get("/"))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.view().name("index"));
    // }

    // @Test
    // void testGetIndexPage() {
    //     // given
    //     Set<Recipe> recipes = new HashSet<>();
    //     recipes.add(new Recipe());

    //     Recipe recipe = new Recipe();
    //     recipe.setId("1");

    //     recipes.add(recipe);

    //     when(recipeService.getRecipes()).thenReturn(Flux.fromIterable(recipes));

    //     ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

    //     // when
    //     String viewName = controller.getIndexPage(model);

    //     // then
    //     assertEquals("index", viewName);
    //     verify(recipeService, times(1)).getRecipes();
    //     verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
    //     List<Recipe> recipeList = argumentCaptor.getValue();
    //     assertEquals(2, recipeList.size());
    // }
}
