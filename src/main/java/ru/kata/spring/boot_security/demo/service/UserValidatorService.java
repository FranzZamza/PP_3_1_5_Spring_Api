package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Optional;

@Service
public class UserValidatorService {
    private final UserRepository userRepository;
    public UserValidatorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> loadUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}
