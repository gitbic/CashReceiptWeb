package ru.clevertec.cashReceiptWeb.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "discount_card")
public class DiscountCard {

    @Id
    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "discount")
    private double discount;
}
