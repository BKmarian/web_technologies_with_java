package com.example.lab7.controller;

import java.util.List;

public class OrderRepresentation {
    private String username;
    private List<String> products;

    public OrderRepresentation() {
    }

    public OrderRepresentation(String username, List<String> products) {
        this.username = username;
        this.products = products;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getProducts() {
        return products;
    }
}
