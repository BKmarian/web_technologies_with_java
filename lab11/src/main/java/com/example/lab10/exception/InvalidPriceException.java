package com.example.lab10.exception;

public class InvalidPriceException  extends RuntimeException   {
    public InvalidPriceException() {
        super("The given price is not valid!");
    }
}
