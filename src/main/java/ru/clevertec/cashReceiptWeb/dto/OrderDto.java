package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    List<PurchaseFullResponseDto> purchaseFullResponseDtoList;
    OrderCostDto orderCostDto;
}
