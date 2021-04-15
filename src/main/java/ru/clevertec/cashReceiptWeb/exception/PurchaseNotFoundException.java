package ru.clevertec.cashReceiptWeb.exception;

import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException() {
    }

    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public PurchaseNotFoundException(PurchaseId purchaseId) {
        this(String.format("User with id \"%s\" not found", purchaseId));
    }
}
