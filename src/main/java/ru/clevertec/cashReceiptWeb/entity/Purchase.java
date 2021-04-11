package ru.clevertec.cashReceiptWeb.entity;

import lombok.Data;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase")
@IdClass(PurchaseId.class)
public class Purchase {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_number", nullable = false)
    private Integer productNumber;
}
