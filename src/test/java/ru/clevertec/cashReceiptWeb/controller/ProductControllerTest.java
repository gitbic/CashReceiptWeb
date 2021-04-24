package ru.clevertec.cashReceiptWeb.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.cashReceiptWeb.dto.ProductRequestDto;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductControllerTest {

    ProductRequestDto testProductRequestDto;


    @BeforeEach
    public void initTestProductRequestDto() {
        testProductRequestDto = ProductRequestDto
                .builder()
                .name("Apple")
                .price(new BigDecimal("1.12"))
                .isDiscount(true)
                .build();
    }

    @Test
    public void productGetTest() {
        String url = "http://localhost:8192/products/1";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ProductResponseDto> resultResponseEntity =
                restTemplate.getForEntity(url, ProductResponseDto.class);

        ProductResponseDto resultProductDto = resultResponseEntity.getBody();

        assert resultProductDto != null;
        assertEquals(testProductRequestDto.getName(), resultProductDto.getName());
        assertEquals(testProductRequestDto.getPrice(), resultProductDto.getPrice());
    }

    @Test
    public void productUpdateTest() {

        String url = "http://localhost:8192/products/5";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ProductRequestDto> requestBody = new HttpEntity<>(testProductRequestDto);

        ResponseEntity<ProductResponseDto> resultResponseEntity =
                restTemplate.exchange(url, HttpMethod.PUT, requestBody, ProductResponseDto.class);

        ProductResponseDto resultProductDto = resultResponseEntity.getBody();

        assert resultProductDto != null;
        assertEquals(testProductRequestDto.getName(), resultProductDto.getName());
        assertEquals(testProductRequestDto.getPrice(), resultProductDto.getPrice());
    }

    @Test
    public void productAddTest() {

        String url = "http://localhost:8192/products/";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<ProductRequestDto> requestBody = new HttpEntity<>(testProductRequestDto);

        ResponseEntity<ProductResponseDto> resultResponseEntity =
                restTemplate.postForEntity(url, requestBody, ProductResponseDto.class);

        ProductResponseDto resultProductDto = resultResponseEntity.getBody();

        assert resultProductDto != null;
        assertEquals(testProductRequestDto.getName(), resultProductDto.getName());
        assertEquals(testProductRequestDto.getPrice(), resultProductDto.getPrice());
    }

    @Test
    public void purchaseDeleteTest() {

        String url = "http://localhost:8192/products/15";
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(url);

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restTemplate.getForEntity(url, ProductResponseDto.class);
        });
    }
}
