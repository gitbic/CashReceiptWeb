package ru.clevertec.cashReceiptWeb.security.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "notEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notEmpty");

        int usernameLength = user.getUsername().length();
        int passwordLength = user.getPassword().length();

        if (usernameLength < 6 || usernameLength > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUserName(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (passwordLength < 8 || passwordLength > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("password", "Diff.userForm.passwordConfirm");
        }

    }
}
