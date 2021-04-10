package ru.clevertec.cashReceiptWeb.entity.pkey;

import lombok.Data;

import java.io.Serializable;

@Data
public class PurchasePkey implements Serializable {
    private Long userId;
    private Long productId;
}
