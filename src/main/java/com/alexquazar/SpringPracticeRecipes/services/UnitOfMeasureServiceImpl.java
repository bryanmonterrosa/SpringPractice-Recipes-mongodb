package com.alexquazar.SpringPracticeRecipes.services;

import org.springframework.stereotype.Service;

import com.alexquazar.SpringPracticeRecipes.commands.UnitOfMeasureCommand;
import com.alexquazar.SpringPracticeRecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.alexquazar.SpringPracticeRecipes.repositories.reactive.UnitOfMeasureReactiveRepository;

import reactor.core.publisher.Flux;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository,
            UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {

        return unitOfMeasureReactiveRepository
                .findAll()
                .map(unitOfMeasureToUnitOfMeasureCommand::convert);

        // return StreamSupport.stream(unitOfMeasureRepository.findAll()
        // .spliterator(), false)
        // .map(unitOfMeasureToUnitOfMeasureCommand::convert)
        // .collect(Collectors.toSet());
    }
}
