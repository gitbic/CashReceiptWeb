package ru.clevertec.cashReceiptWeb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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

    public PurchaseId getPurchaseId() {
        return new PurchaseId(userId, productId);
    }
}
