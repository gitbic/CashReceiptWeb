package ru.clevertec.cashReceiptWeb.exception;

public class UsernameExistException extends RuntimeException{
    public UsernameExistException() {
    }

    public UsernameExistException(String username) {
        super(String.format("Username \"%s\" is already exist", username));
    }
}
