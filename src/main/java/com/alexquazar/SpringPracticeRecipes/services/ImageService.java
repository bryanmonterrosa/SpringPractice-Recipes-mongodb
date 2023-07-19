package com.alexquazar.SpringPracticeRecipes.services;

import java.util.concurrent.ExecutionException;

import org.springframework.web.multipart.MultipartFile;

import reactor.core.publisher.Mono;

public interface ImageService {

    Mono<Void> saveImageFile(String recipeId, MultipartFile file) throws InterruptedException, ExecutionException;
}