package ru.clevertec.cashReceiptWeb.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseId implements Serializable {
    private Long userId;
    private Long productId;
}
