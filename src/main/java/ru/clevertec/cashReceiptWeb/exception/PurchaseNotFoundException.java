package ru.clevertec.cashReceiptWeb.exception;

import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

public class PurchaseNotFoundException extends EntityNotFoundException{
    public PurchaseNotFoundException() {
    }

    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public PurchaseNotFoundException(PurchaseId purchaseId) {
        super(String.format("Purchase with id '%s' not found", purchaseId));
    }
}
