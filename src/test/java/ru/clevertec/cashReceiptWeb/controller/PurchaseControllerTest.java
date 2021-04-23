package ru.clevertec.cashReceiptWeb.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PurchaseControllerTest {

    ModelMapper modelMapper;
    PurchaseRequestDto testPurchaseRequestDto;


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
    public void purchaseGetTest() {
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

    @Test
    public void purchaseExistingAddTest() {

        String url = "http://localhost:8192/purchases/";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<PurchaseRequestDto> requestBody = new HttpEntity<>(testPurchaseRequestDto);

        ResponseEntity<PurchaseSimpleResponseDto> resultResponseEntity =
                restTemplate.postForEntity(url, requestBody, PurchaseSimpleResponseDto.class);

        PurchaseSimpleResponseDto resultPurchaseDto = resultResponseEntity.getBody();

        assert resultPurchaseDto != null;
        assertEquals(testPurchaseRequestDto.getProductNumber() * 2, resultPurchaseDto.getProductNumber());
    }

    @Test
    public void purchaseNewAddTest() {

        String url = "http://localhost:8192/purchases/";

        RestTemplate restTemplate = new RestTemplate();


        PurchaseRequestDto newPurchaseRequestDto = testPurchaseRequestDto
                .toBuilder()
                .productId(20L)
                .build();

        HttpEntity<PurchaseRequestDto> requestBody = new HttpEntity<>(newPurchaseRequestDto);

        ResponseEntity<PurchaseSimpleResponseDto> resultResponseEntity =
                restTemplate.postForEntity(url, requestBody, PurchaseSimpleResponseDto.class);

        PurchaseSimpleResponseDto resultPurchaseDto = resultResponseEntity.getBody();

        assert resultPurchaseDto != null;
        assertEquals(newPurchaseRequestDto.getProductNumber(), resultPurchaseDto.getProductNumber());
    }

    @Test
    public void purchaseDeleteTest() {

        String url = "http://localhost:8192/purchases?userId=1&productId=3";
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(url);

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restTemplate.getForEntity(url, PurchaseSimpleResponseDto.class);
        });
    }
}
