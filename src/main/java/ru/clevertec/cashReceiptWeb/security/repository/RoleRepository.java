package ru.clevertec.cashReceiptWeb.security.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.Role;

import java.util.Set;

@Repository
public interface RoleRepository {
    Role findById(Long id);

    void save(Role role);

    void delete(Long id);

    Set<Role> findAll();

    Set<Role> findAllByUserId(Long userId);
}
