package ru.clevertec.cashReceiptWeb.exception;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long id) {
        super(String.format("Product with id '%s' not found", id));
    }
}
