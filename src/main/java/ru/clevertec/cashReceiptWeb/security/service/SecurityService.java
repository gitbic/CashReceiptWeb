package ru.clevertec.cashReceiptWeb.security.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
