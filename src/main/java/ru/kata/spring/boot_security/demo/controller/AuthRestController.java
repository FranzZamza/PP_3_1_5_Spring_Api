package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.validator.UserValidator;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthRestController {
    private final RegistrationService registrationService;
    private final UserValidator validator;

    public AuthRestController(RegistrationService registrationService, UserValidator validator) {
        this.registrationService = registrationService;
        this.validator = validator;
    }

    @PostMapping(value = "/registration")
    ResponseEntity<HttpStatus> registration(@RequestBody User user, BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            //TODO
        }
        registrationService.register(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
