package com.alexquazar.SpringPracticeRecipes.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(NumberFormatException.class)
    // public ModelAndView handleNumberFormat(Exception exception) {

    //     log.error("Handling Number Format Excepton");
    //     String errorMessage = exception.getMessage();
    //     log.error(errorMessage);

    //     ModelAndView modelAndView = new ModelAndView();

    //     modelAndView.setViewName("400error");
    //     modelAndView.addObject("exception", exception);

    //     return modelAndView;

    // }
}
