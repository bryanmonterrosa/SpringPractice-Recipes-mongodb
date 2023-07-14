package com.alexquazar.SpringPracticeRecipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.alexquazar.SpringPracticeRecipes.model.Category;

public interface CategoryRepository extends CrudRepository<Category, String>{
    
    Optional<Category> findByDescription(String description);
}
