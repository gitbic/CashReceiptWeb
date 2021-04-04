package ru.clevertec.cashReceiptWeb.security.service;

import ru.clevertec.cashReceiptWeb.security.model.User;

import java.util.Set;

public interface UserService {
    User findById(Long id);

    User findByUserName(String username);

    void save(User user);

    void delete(Long id);

    Set<User> findAll();
}
