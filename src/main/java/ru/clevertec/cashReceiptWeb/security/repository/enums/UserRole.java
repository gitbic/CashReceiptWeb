package ru.clevertec.cashReceiptWeb.security.repository.enums;

public enum UserRole {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    private final int roleId;

    UserRole(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
