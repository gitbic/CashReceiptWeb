package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.OrderDto;

public interface OrderService {

    OrderDto getOrderDto(Long userId);
}
