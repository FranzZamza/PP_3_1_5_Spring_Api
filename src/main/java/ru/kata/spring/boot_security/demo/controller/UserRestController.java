package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    ResponseEntity<User> getUser(Principal principal) throws RuntimeException {
        Optional<User> user = userService.getUserByUsername(principal.getName());
        if (user.isEmpty()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    @GetMapping("/rolesString")
    ResponseEntity<String> getUserRolesName(Principal principal) {
        Optional<User> user = userService.getUserByUsername(principal.getName());
        //переделать
        return new ResponseEntity<>(user.orElse(new User()).getRolesName(), HttpStatus.OK);
    }
}
