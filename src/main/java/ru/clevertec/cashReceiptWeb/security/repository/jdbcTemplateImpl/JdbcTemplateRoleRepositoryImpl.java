package ru.clevertec.cashReceiptWeb.security.repository.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.repository.RoleRepository;
import ru.clevertec.cashReceiptWeb.security.repository.mapper.RoleMapper;

import java.util.Set;

@Repository
public class JdbcTemplateRoleRepositoryImpl implements RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role findById(Long id) {
        String sqlQuery = "SELECT * FROM role WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new RoleMapper(), id);
    }

    @Override
    public Role findByUsername(String name) {
        return null;
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Set<Role> findAll() {
        return null;
    }
}
