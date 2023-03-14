package ru.kata.spring.boot_security.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserValidatorService;

@Component
public class UserValidator implements Validator {
    private final UserValidatorService validatorService;

    public UserValidator(UserValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (user.getPassword().isEmpty()) {
            errors.rejectValue("password", "", "Пустой пароль!");
        }

        if (user.getLastname().isEmpty()) {
            errors.rejectValue("lastname", "", "Осутствует фамилия пользователя");
        }

        if (user.getEmail().isEmpty()) {
            errors.rejectValue("email", "", "Пустой email!");
        }

        if (user.getUsername().isEmpty()) {
            errors.rejectValue("username", "", "Пустое имя пользователя");
        }

        if (user.getAge() == null) {
            errors.rejectValue("age", "", "Пустое поле с возрастом");
        }

        if (validatorService.loadUserByName(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "Пользователь с таким именем существует");
        }

    }
}
