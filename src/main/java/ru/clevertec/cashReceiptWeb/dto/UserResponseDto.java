package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String password;
    private String cardNumber;
}
