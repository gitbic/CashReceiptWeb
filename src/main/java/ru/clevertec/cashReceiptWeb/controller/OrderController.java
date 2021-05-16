package ru.clevertec.cashReceiptWeb.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.OrderDto;
import ru.clevertec.cashReceiptWeb.service.OrderService;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public OrderDto getOrderDto(@PathVariable Long userId) {
        log.info("Method: {}, input value: userId = {}", "getOrderDto", userId);

        OrderDto orderDto = orderService.getOrderDto(userId);

        log.info("Method: {}, output value: {}", "getOrderDto", orderDto);
        return orderDto;
    }


    @GetMapping("/cashReceipt")
    public String printCashReceipt(@RequestParam Long userId) {
        log.info("Method: {}, input value: userId = {}", "printCashReceipt", userId);

        String printedCashReceiptUrl = orderService.printCashReceipt(userId);

        log.info("Method: {}, output value: cashReceiptUrl = {}", "printCashReceipt", printedCashReceiptUrl);
        return printedCashReceiptUrl;
    }

}
