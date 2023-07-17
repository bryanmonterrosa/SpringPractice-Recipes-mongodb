package com.alexquazar.SpringPracticeRecipes.repositories.reactive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.alexquazar.SpringPracticeRecipes.model.Category;

@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @BeforeEach
    public void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() {
        Category category = new Category();
        category.setDescription("Mexican");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test

    void testFindByDescription() {
        Category category = new Category();
        category.setDescription("foo");

        categoryReactiveRepository.save(category).block();

        Category fetchedCat = categoryReactiveRepository.findByDescription("foo").block();

        assertNotNull(fetchedCat.getId());

    }
}
