package com.example.lab7.controller;

import com.example.lab7.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<String>> addOrder(@RequestBody OrderRepresentation orderRepresentation) {
        //primim o cerere de comanda, cu username-ul userului care a initiat-o si denumirile produselor pe care acesta le doreste
        List<String> productsNotFound = orderService.order(orderRepresentation.getUsername(), orderRepresentation.getProducts());

        if(productsNotFound.isEmpty()) {
            //daca toate produsele cerute au fost gasite, comanda s-a efectuat
            return new ResponseEntity<>(HttpStatus.OK);
        }
        //exista produse ce nu au fost gasite, pe care le intoarcem userului, ca sa stie ce sa scoata din comanda
        return new ResponseEntity<>(productsNotFound, HttpStatus.BAD_REQUEST);
    }
}
