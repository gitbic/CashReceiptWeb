package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.OrderDto;
import ru.clevertec.cashReceiptWeb.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public OrderDto getOrderDto(@PathVariable Long userId) {
        return orderService.getOrderDto(userId);
    }

    @GetMapping("/cashreceipt")
    public String printOrderCheck(@RequestParam Long userId, @RequestParam(defaultValue = "txt") String printType) {
        // todo send order dto to CashReceiptPrinter
        OrderDto orderDto = orderService.getOrderDto(userId);
        return "check";
    }

}
