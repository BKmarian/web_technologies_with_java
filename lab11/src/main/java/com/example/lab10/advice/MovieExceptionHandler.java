package com.example.lab10.advice;

import com.example.lab10.exception.InvalidPriceException;
import com.example.lab10.exception.NoProductFoundException;
import com.example.lab10.exception.NoStockAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieExceptionHandler {

    @ExceptionHandler(value = {
            NoProductFoundException.class,
            NoStockAvailableException.class,
            InvalidPriceException.class
    })
    public ResponseEntity<String> handleNotFoundMovie(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
