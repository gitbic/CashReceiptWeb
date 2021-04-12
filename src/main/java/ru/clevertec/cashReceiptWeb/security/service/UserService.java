package ru.clevertec.cashReceiptWeb.security.service;

import ru.clevertec.cashReceiptWeb.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);

    Optional<User> findUserByUserName(String username);

    boolean addUser(User user);

    User updateUser(Long id, User newUser);

    User saveUser(User user);

    void deleteUserById(Long id);

    List<User> findAllUsers();
}
