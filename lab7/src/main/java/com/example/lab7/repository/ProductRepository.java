package com.example.lab7.repository;

import com.example.lab7.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        products = initialProducts();
    }

    public Optional<Product> get(String name) {
        return products.stream().filter(product -> product.getName().equals(name)).findFirst();
    }

    private List<Product> initialProducts() {
        return new ArrayList<>(asList(
                new Product("lapte"), new Product("pampers"), new Product("bere")
        ));
    }
}
