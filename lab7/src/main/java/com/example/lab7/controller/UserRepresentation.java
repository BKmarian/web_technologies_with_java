package com.example.lab7.controller;

import com.example.lab7.model.Product;
import com.example.lab7.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepresentation {
    private String id;
    private String username;
    private String email;
    private String password;

    private List<OrderRepresentation> orders;

    public UserRepresentation() {
    }

    public UserRepresentation(User user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();

        orders = user.getOrders().stream().map(order ->
                new OrderRepresentation(username, order.getProducts().stream().map(Product::getName).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<OrderRepresentation> getOrders() {
        return orders;
    }
}
