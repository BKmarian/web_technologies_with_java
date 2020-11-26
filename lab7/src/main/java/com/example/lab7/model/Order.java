package com.example.lab7.model;

import java.util.List;
import java.util.UUID;

//@Data
//@Getter @Setter
//adnotari care ar fi trebuit sa declanseze crearea constructuctorului, get-erilor si set-erilor; dar nu au mers la mine in local

public class Order{
    private String id;
    private User user;
    private List<Product> products;

    public Order() {
        //constructor necesar pt serializare/deserializare
    }

    public Order(User user, List<Product> products) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }
}
