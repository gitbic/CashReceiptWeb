package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.clevertec.cashReceiptWeb.dto.OrderDto;
import ru.clevertec.cashReceiptWeb.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${cash-receipt-printer.url}")
    private String cashReceiptPrinterUrl;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public OrderDto getOrderDto(@PathVariable Long userId) {
        return orderService.getOrderDto(userId);
    }

    @GetMapping("/cashReceipt")
    public String printOrderCheck(@RequestParam Long userId) {
        OrderDto orderDto = orderService.getOrderDto(userId);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrderDto> requestBody = new HttpEntity<>(orderDto);

        ResponseEntity<String> resultResponseEntity =
                restTemplate.postForEntity(cashReceiptPrinterUrl, requestBody, String.class);

        if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
            return resultResponseEntity.getBody();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }

}
