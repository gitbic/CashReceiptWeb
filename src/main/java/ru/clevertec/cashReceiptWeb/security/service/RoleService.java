package ru.clevertec.cashReceiptWeb.security.service;

import ru.clevertec.cashReceiptWeb.security.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findAllByUserId(Long userId);
}
