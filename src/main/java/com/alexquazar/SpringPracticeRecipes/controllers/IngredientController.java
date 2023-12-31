package com.alexquazar.SpringPracticeRecipes.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.alexquazar.SpringPracticeRecipes.commands.IngredientCommand;
import com.alexquazar.SpringPracticeRecipes.commands.RecipeCommand;
import com.alexquazar.SpringPracticeRecipes.commands.UnitOfMeasureCommand;
import com.alexquazar.SpringPracticeRecipes.services.IngredientService;
import com.alexquazar.SpringPracticeRecipes.services.RecipeService;
import com.alexquazar.SpringPracticeRecipes.services.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    private WebDataBinder webDataBinder;

    @InitBinder("ingredient")
    public void initBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

    public IngredientController(IngredientService ingredientService, RecipeService recipeService,
            UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
            @PathVariable String id, Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
            @PathVariable String id, Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute("ingredient") IngredientCommand command, @PathVariable String recipeId, Model model)
            throws InterruptedException, ExecutionException {

        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            
            model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
            return "recipe/ingredient/ingredientform";
        }

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command).toFuture().get();

        log.debug("saved recipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model)
            throws InterruptedException, ExecutionException {

        // make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId).toFuture().get();
        // todo raise exception if null

        // need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);

        // init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
            @PathVariable String id) throws InterruptedException, ExecutionException {

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(recipeId, id);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @ModelAttribute("uomList")
    public Flux<UnitOfMeasureCommand> populateUomList(){
        return unitOfMeasureService.listAllUoms();
    }
}
