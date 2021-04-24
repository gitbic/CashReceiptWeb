package ru.clevertec.cashReceiptWeb.security.repository.enums;

public enum UserRole {
    ROLE_ADMIN(1L),
    ROLE_USER(2L);

    private final Long roleId;

    UserRole(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
