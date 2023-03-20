package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/{id}")
    User getUser(@PathVariable Long id) {
        return userService.getUserById(id).orElse(new User());
    }

    @GetMapping(value = "/users")
    ResponseEntity<List<User>> getUsers() {
        userService.getUsers().forEach(User::getRoles);
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    ResponseEntity<HttpStatus> newUser(@RequestBody User newUser) {
        userService.save(newUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/edit/{id}")
    ResponseEntity<HttpStatus> updateUser(@RequestBody User updateUser,
                                          @PathVariable(value = "id") Long id) {
        userService.update(id, updateUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
