package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private List<PurchaseFullResponseDto> purchaseFullResponseDtoList;
    private OrderCostDto orderCostDto;
    private String username;
    private double discountPercentByCard;
}
