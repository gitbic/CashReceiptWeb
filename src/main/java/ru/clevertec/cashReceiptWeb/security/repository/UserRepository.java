package ru.clevertec.cashReceiptWeb.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByUsername(String username);
    User findByUsername(String username);

//    void add(User user);
}
