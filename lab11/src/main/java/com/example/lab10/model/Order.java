package com.example.lab10.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Order {

    private int id;
    private double totalPrice;
    private Status status;
    private List<Product> products;
}
