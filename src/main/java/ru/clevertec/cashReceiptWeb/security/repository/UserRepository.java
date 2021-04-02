package ru.clevertec.cashReceiptWeb.security.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.User;

import java.util.Set;

@Repository
public interface UserRepository {
    User findById(Long id);

    User findByUsername(String username);

    void save(User user);

    void delete(Long id);

    Set<User> findAll();
}
