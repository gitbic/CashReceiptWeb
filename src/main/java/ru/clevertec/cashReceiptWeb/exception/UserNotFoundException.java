package ru.clevertec.cashReceiptWeb.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String userName) {
        super(String.format("User with name \"%s\" not found", userName));
    }

    public UserNotFoundException(Long id) {
        super(String.format("User with id \"%s\" not found", id));
    }
}
