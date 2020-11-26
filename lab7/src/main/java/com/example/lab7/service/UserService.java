package com.example.lab7.service;

import com.example.lab7.model.User;
import com.example.lab7.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {return userRepository.getAll();}

    public User getByUsername(String username) {
        return userRepository.getByUsername(username).orElseThrow(NoSuchElementException::new);
    }

    public boolean addUser(User user) {

        if(userRepository.getByUsername(user.getUsername()).isPresent()) {
            //mai exista un user cu username-ul dat, nu putem adauga userul primit
            return false;
        }

        //id-ul unic la userului se genereaza in constructorul parametrizat in aceasta app;
        //am creat un nou user, cu toate datele de la cel dat, pt care se genereaza id-ul unic
        User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
        userRepository.addUser(newUser);
        return true;
    }
}
