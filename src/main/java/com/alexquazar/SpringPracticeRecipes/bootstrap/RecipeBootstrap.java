package com.alexquazar.SpringPracticeRecipes.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alexquazar.SpringPracticeRecipes.model.Category;
import com.alexquazar.SpringPracticeRecipes.model.Difficulty;
import com.alexquazar.SpringPracticeRecipes.model.Ingredient;
import com.alexquazar.SpringPracticeRecipes.model.Notes;
import com.alexquazar.SpringPracticeRecipes.model.Recipe;
import com.alexquazar.SpringPracticeRecipes.model.UnitOfMeasure;
import com.alexquazar.SpringPracticeRecipes.repositories.CategoryRepository;
import com.alexquazar.SpringPracticeRecipes.repositories.RecipeRepository;
import com.alexquazar.SpringPracticeRecipes.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadCategories();
        loadUom();
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
    }

    private void loadCategories(){
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Each");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Pint");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setDescription("Dash");
        unitOfMeasureRepository.save(uom8);
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        // get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if (!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        // get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        // get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Guacamole");
        guacRecipe.setPrepTime(15);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections(
                """
                        1 Mash avocados, lime juice, and salt together in a medium bowl; mix in tomatoes, onion, cilantro, and garlic. Stir in cayenne pepper.

                        2 Serve immediately, or cover and refrigerate for 1 hour for improved flavor.
                            """);
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes(
                """
                        Avocados have a tendency to turn brown when they're exposed to air, so guacamole loses its brightness rather quickly. You can prevent this by placing the guacamole in an airtight container, then covering the top with a thin layer of water, lemon juice, or lime juice. This barrier will keep the air from getting in. Seal the container and store it in the refrigerator for up to two days.

                        Read More at https://www.allrecipes.com/recipe/14231/guacamole/
                        """);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("Ripe avocados", new BigDecimal(3), eachUom));
        guacRecipe.addIngredient(new Ingredient("Lime or Lemon Juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(1), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Diced roma tomatoes", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Diced Onion", new BigDecimal(0.5), cupsUom));
        guacRecipe.addIngredient(new Ingredient("Chopped Fresh Cilantro", new BigDecimal(3), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Minced Garlic", new BigDecimal(1), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Ground cayenne pepper", new BigDecimal(1), pinchUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        guacRecipe.setUrl("https://www.allrecipes.com/recipe/14231/guacamole/");
        guacRecipe.setServings(4);
        guacRecipe.setSource("allrecipes");

        // add to return list
        recipes.add(guacRecipe);

        // Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Baja Grilled Chicken Tacos");
        tacosRecipe.setCookTime(15);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections(
                """
                        1 Combine cumin, oregano, sazón, garlic powder, salt, and cayenne pepper in a large bowl. Add lime juice and olive oil and stir to make marinade. Add chicken and refrigerate for 30 minutes to 12 hours.

                        2 Preheat an outdoor grill for medium heat and lightly oil the grate. Grill marinated chicken until no longer pink at the bone and juices run clear, about 8 minutes per side. Transfer to a bowl and shred chicken with forks.

                        3 Spray canola oil onto tortillas and heat on the grill approximately 45 seconds per side. Transfer to a plate and cover with a paper towel to keep warm. Serve with shredded chicken.
                            """);

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes(
                """
                        As an alternative to grilling, heat 1 tablespoon of canola or olive oil in a large skillet over medium-high heat. When the oil is hot, add seasoned chicken and sauté until cooked through. Heat tortillas per package directions and serve with chicken and garnishes of your choice.

                        This dish pairs well with a black bean and corn salad.

                        Read More at https://www.allrecipes.com/recipe/269262/baja-grilled-chicken-tacos/
                                """);

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("Cumin", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Mexican oregano", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sazón seasoning", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Garlic powder", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Cayenne pepper", new BigDecimal(.25), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Lime or Lemon Juice", new BigDecimal(.25), cupsUom));
        tacosRecipe.addIngredient(new Ingredient("Olive oil", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Skinless, boneless chicken thighs", new BigDecimal(4), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Package corn tortillas", new BigDecimal(1), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        tacosRecipe.setUrl("https://www.allrecipes.com/recipe/269262/baja-grilled-chicken-tacos/");
        tacosRecipe.setServings(4);
        tacosRecipe.setSource("allrecipes");

        recipes.add(tacosRecipe);
        return recipes;
    }
}
