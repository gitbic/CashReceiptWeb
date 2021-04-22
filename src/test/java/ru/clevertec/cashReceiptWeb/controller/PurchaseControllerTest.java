package ru.clevertec.cashReceiptWeb.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PurchaseControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    Purchase testPurchase;
    PurchaseRequestDto testPurchaseRequestDto;

//    @BeforeEach
//    public void initTestPurchase() {
//        testPurchase = Purchase
//                .builder()
//                .userId(1L)
//                .productId(3L)
//                .productNumber(4)
//                .build();
//    }

    @BeforeEach
    public void initTestPurchaseRequestDto() {
        testPurchaseRequestDto = PurchaseRequestDto
                .builder()
                .userId(1L)
                .productId(3L)
                .productNumber(4)
                .build();
    }

    @Test
    public void purchaseAddTest() {
        String url = "http://localhost:8192/purchases?userId=1&productId=3";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PurchaseSimpleResponseDto> resultResponseEntity =
                restTemplate.getForEntity(url, PurchaseSimpleResponseDto.class);

        PurchaseSimpleResponseDto resultPurchaseDto = resultResponseEntity.getBody();

        assert resultPurchaseDto != null;
        assertEquals(testPurchaseRequestDto.getProductNumber(), resultPurchaseDto.getProductNumber());
    }

    @Test
    public void purchaseUpdateTest() {

        String url = "http://localhost:8192/purchases?userId=1&productId=3";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PurchaseRequestDto> requestBody = new HttpEntity<>(testPurchaseRequestDto);

        ResponseEntity<PurchaseSimpleResponseDto> resultResponseEntity =
                restTemplate.exchange(url, HttpMethod.PUT, requestBody, PurchaseSimpleResponseDto.class);

        PurchaseSimpleResponseDto resultPurchaseDto = resultResponseEntity.getBody();

        assert resultPurchaseDto != null;
        assertEquals(testPurchaseRequestDto.getProductNumber(), resultPurchaseDto.getProductNumber());
    }
}
