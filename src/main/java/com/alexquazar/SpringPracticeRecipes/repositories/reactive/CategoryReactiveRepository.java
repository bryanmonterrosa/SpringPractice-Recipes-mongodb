package com.alexquazar.SpringPracticeRecipes.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.alexquazar.SpringPracticeRecipes.model.Category;

import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByDescription(String description);
}
