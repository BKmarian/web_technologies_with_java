package com.example.lab6example1.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleOtherExceptions(Exception exception)    {
        return "New exception detected!";
    }
}
