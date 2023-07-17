package com.alexquazar.SpringPracticeRecipes.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notes {

    @Id
    private String id;
    private Recipe recipe;
    private String recipeNotes;

}
