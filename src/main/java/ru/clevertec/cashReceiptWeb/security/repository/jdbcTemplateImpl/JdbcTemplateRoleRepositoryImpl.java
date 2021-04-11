package ru.clevertec.cashReceiptWeb.security.repository.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.repository.RoleRepository;
import ru.clevertec.cashReceiptWeb.security.repository.mapper.RoleMapper;

import java.util.HashSet;
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
    public void save(Role role) {
        String sqlQuery = "INSERT INTO role (id, role_name) VALUES (?,?)";
        jdbcTemplate.update(sqlQuery, role.getId(), role.getName());
    }

    @Override
    public void delete(Long id) {
        String sqlQuery = "DELETE FROM role WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public Set<Role> findAll() {
        String sqlQuery = "SELECT * FROM role";
        return new HashSet<>(jdbcTemplate.query(sqlQuery, new RoleMapper()));
    }

    @Override
    public Set<Role> findAllByUserId(Long userId) {
        String sqlQuery = "SELECT r.id, r.role_name FROM role r, user_role ur WHERE r.id = ur.role_id and ur.user_id = ?";
        return new HashSet<>(jdbcTemplate.query(sqlQuery, new RoleMapper(), userId));
    }
}
