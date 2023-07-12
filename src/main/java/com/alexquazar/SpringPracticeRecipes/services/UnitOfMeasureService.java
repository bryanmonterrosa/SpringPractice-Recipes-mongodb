package com.alexquazar.SpringPracticeRecipes.services;

import java.util.Set;

import com.alexquazar.SpringPracticeRecipes.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}