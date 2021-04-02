package ru.clevertec.cashReceiptWeb.security.service;

import ru.clevertec.cashReceiptWeb.security.model.User;

public interface UserService {
    void save(User user);

    User findByUserName(String username);
}
