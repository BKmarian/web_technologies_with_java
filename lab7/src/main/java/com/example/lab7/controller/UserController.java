package com.example.lab7.controller;

import com.example.lab7.model.User;
import com.example.lab7.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserRepresentation> getAllUsers() {
        //initial a fost
        // return userService.getAll();
        //incercati sa vedeti ce se intampla, daca unul dintre useri a executat deja o comanda
        //la serializare, se serilizare userul, care continea comenzi (Order), fiecare comanda continea userul, care iar se serializa, deci devenea o bucla infinita
        //pentru a rupe aceasta bucla, am intors o representare a userlui, care nu mai contine o lista de Order, ci de OrderRepresention,
        //iar o representare de comanda contine doar un username, nu un obiect intreg de user
        return userService.getAll().stream().map(UserRepresentation::new).collect(Collectors.toList());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(userService.getByUsername(username), OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        boolean isUserAdded = userService.addUser(user);
        return isUserAdded ? new ResponseEntity<>(OK) : new ResponseEntity<>(BAD_REQUEST);
    }
}
