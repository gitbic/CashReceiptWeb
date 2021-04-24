package ru.clevertec.cashReceiptWeb.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.clevertec.cashReceiptWeb.dto.OrderDto;
import ru.clevertec.cashReceiptWeb.service.OrderService;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @Value("${cash-receipt-printer.url}")
    private String cashReceiptPrinterUrl;

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

        OrderDto orderDto = orderService.getOrderDto(userId);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrderDto> requestBody = new HttpEntity<>(orderDto);

        ResponseEntity<String> resultResponseEntity =
                restTemplate.postForEntity(cashReceiptPrinterUrl, requestBody, String.class);

        if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
            String printedCashReceiptUrl = resultResponseEntity.getBody();

            log.info("Method: {}, output value: {}", "printCashReceipt", printedCashReceiptUrl);
            return printedCashReceiptUrl;

        } else {

            log.warn("Method: {}, output value: {}", "printCashReceipt", "Cash receipt printing error");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }

}
