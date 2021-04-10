package ru.clevertec.cashReceiptWeb.entity;

import lombok.Data;
import ru.clevertec.cashReceiptWeb.entity.pkey.PurchasePkey;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase")
@IdClass(PurchasePkey.class)
public class Purchase {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_number")
    private Integer productNumber;
}
