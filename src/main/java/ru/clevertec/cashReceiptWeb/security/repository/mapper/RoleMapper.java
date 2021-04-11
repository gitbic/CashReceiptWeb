package ru.clevertec.cashReceiptWeb.security.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.cashReceiptWeb.security.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();

        role.setId(rs.getLong("id"));
        role.setName(rs.getString("role_name"));

        return role;
    }
}
