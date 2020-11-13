package com.example.lab6example1.exception;

public class InvalidFoodProductException extends RuntimeException  {
    public InvalidFoodProductException(String message) {
        super(message);
    }
}
