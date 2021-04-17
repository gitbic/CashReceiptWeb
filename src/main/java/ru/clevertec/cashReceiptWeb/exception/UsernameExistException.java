package ru.clevertec.cashReceiptWeb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class UsernameExistException extends RuntimeException{
    public UsernameExistException() {
    }

    public UsernameExistException(String username) {
        super(String.format("Username '%s' is already exist", username));
    }
}
