package ru.clevertec.cashReceiptWeb.exception;

public class DiscountCardNotFoundException extends RuntimeException{
    public DiscountCardNotFoundException() {
    }

    public DiscountCardNotFoundException(String cardNumber) {
        super(String.format("Discount card with number \"%s\" not found", cardNumber));
    }
}
