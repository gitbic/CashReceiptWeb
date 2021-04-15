package ru.clevertec.cashReceiptWeb.security.service;

import ru.clevertec.cashReceiptWeb.security.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRolesByUserId(Long userId);
}
