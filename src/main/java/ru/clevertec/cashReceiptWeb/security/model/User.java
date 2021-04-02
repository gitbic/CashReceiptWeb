package ru.clevertec.cashReceiptWeb.security.model;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;
}
