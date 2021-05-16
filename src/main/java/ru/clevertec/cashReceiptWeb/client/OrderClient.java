package ru.clevertec.cashReceiptWeb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.clevertec.cashReceiptWeb.dto.OrderDto;

@FeignClient(name = "cash-receipt-printer")
public interface OrderClient {

    @PostMapping("/cashReceipt")
    String printCashReceipt(OrderDto orderDto);
}
