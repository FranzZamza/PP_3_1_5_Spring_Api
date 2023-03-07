package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    List<User> getUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    void update(Long id, User user);

    void delete(Long id);
}
