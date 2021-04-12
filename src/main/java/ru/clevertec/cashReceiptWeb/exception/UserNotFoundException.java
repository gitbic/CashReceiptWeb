package ru.clevertec.cashReceiptWeb.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this("User with id \"" + id.toString() + "\" not found");
    }
}
