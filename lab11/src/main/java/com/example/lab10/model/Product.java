package com.example.lab10.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;
    private int availableStock;

    public Product(String name) {
        this.name = name;
    }
}
