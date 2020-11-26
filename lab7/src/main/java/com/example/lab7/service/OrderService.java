package com.example.lab7.service;

import com.example.lab7.model.Order;
import com.example.lab7.model.Product;
import com.example.lab7.model.User;
import com.example.lab7.repository.ProductRepository;
import com.example.lab7.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<String> order(String username, List<String> products) {
        //verificam ca exista userul cu usernameul dat
        //daca nu il gasim, aruncam o exceptie care poate fi handle-uita de controller dupa
        User user = userRepository.getByUsername(username).orElseThrow(NoSuchElementException::new);

        //verificam ca toate produsele din comanda exista
        //intr-o lista vom tine numele produselor negasite
        List<String> productsNotFound = new ArrayList<>();
        //in alta lista produsele Product gasite pe baza denumirii produselor din comanda
        List<Product> productsFound = new ArrayList<>();
        products.forEach(productName -> productRepository.get(productName).ifPresentOrElse(
                //ifPresent, produsul a fost gasit si este adaugat in lista de produse gasite
                product ->productsFound.add(product),
                //else, produsul nu a fost gasit si este adaugat in lista cu denumirile produselor negasite
                () ->productsNotFound.add(productName)
        ));

        if(!productsNotFound.isEmpty()) {
            //exista cel putin un produs care nu a fost gasit, nu putem face comanda
            return productsNotFound;
        }

        //toate produsele au fost gasite, putem efectua comanda
        //creeam comanda si o salvam pe user
        Order order = new Order(user, productsFound);
        user.addOrder(order);

        //returnam o lista goala pt controller (sau productsNotFound; care in acest punct e tot goala)
        //ca acesta sa stie ca operatia a decurs ok
        return Collections.emptyList();
    }
}
