package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;
// TODO del
@Data
public class PurchaseCostViewDto {
    private String totalCost;
    private String finalCost;
    private String discountCost;
}
