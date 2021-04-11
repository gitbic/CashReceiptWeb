package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

@Data
public class PurchaseCostViewDto {
    private String totalCost;
    private String finalCost;
    private String discountCost;
}
