package com.alexquazar.SpringPracticeRecipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.alexquazar.SpringPracticeRecipes.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    
    Optional<UnitOfMeasure> findByDescription(String description);
}
