package com.example.lab7.repository;

import com.example.lab7.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = initialUsers();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAll() {return users;}

    public Optional<User> getByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    private List<User> initialUsers() {
        //daca lasam doar asList(), primeam o lista immutabula si eroare la addUser()
        return new ArrayList<>(asList(
                new User("user1", "user1@email.com", "secret"),
                new User("user2", "user2@email.com", "secret")
        ));
    }
}
