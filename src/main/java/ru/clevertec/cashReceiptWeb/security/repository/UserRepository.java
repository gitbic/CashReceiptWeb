package ru.clevertec.cashReceiptWeb.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_role(user_id, role_id) VALUES (?, ?)", nativeQuery = true)
    void saveUserRole(Long userId, Long roleId);
}
