package ru.clevertec.cashReceiptWeb.beans;

public class DiscountCard {
    private String cardNumber;
    private double discount;

    public DiscountCard(String cardNumber, double discount) {
        this.cardNumber = cardNumber;
        this.discount = discount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", discount=" + discount +
                '}';
    }
}
