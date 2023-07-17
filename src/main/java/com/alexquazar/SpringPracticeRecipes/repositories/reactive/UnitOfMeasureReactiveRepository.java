package com.alexquazar.SpringPracticeRecipes.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.alexquazar.SpringPracticeRecipes.model.UnitOfMeasure;

import reactor.core.publisher.Mono;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {
    Mono<UnitOfMeasure> findByDescription(String description);
}
