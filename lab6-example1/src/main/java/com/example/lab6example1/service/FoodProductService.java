package com.example.lab6example1.service;

import com.example.lab6example1.exception.InvalidFoodProductException;
import com.example.lab6example1.model.FoodProduct;
import org.springframework.stereotype.Service;

@Service
public class FoodProductService {

    public FoodProduct isProductInvalid()   {
        throw new InvalidFoodProductException("invalid");
    }

    public FoodProduct generalExceptionWithProduct()   {
        throw new RuntimeException("invalid");
    }
}
