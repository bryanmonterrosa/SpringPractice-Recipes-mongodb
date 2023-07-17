package com.alexquazar.SpringPracticeRecipes.services;

import com.alexquazar.SpringPracticeRecipes.commands.UnitOfMeasureCommand;

import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}