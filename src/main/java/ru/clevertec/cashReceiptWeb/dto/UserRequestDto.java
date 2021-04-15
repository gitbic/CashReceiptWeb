package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String password;
    private String cardNumber;
}
