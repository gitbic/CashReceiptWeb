package ru.clevertec.cashReceiptWeb.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long id) {
        this(String.format("Product with id \"%s\" not found", id));
    }
}
