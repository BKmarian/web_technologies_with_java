package com.example.lab10.model;

import lombok.Data;

@Data
public class OrderItem {

    private int id;
    private Order order;
    private Product product;
    private int quantity;
}
