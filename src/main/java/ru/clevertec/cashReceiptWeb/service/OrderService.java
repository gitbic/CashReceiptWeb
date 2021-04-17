package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.OrderDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.math.BigDecimal;

public interface OrderService {

    BigDecimal getPurchaseCost(Purchase purchase);

    OrderDto getOrderDto(Long userId);
}
