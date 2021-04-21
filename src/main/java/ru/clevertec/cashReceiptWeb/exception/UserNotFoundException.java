package ru.clevertec.cashReceiptWeb.exception;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(Long id) {
        super(String.format("User with id '%s' not found", id));
    }

    public UserNotFoundException(String name) {
        super(String.format("User with name '%s' not found", name));
    }
}
