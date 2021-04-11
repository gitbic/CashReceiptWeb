package ru.clevertec.cashReceiptWeb.security.service;

import ru.clevertec.cashReceiptWeb.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    User findByUserName(String username);

    boolean add(User user);

    void deleteById(Long id);

    List<User> findAll();
}
