package ru.clevertec.cashReceiptWeb.security.repository.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;
import ru.clevertec.cashReceiptWeb.security.repository.enums.UserRole;
import ru.clevertec.cashReceiptWeb.security.repository.mapper.UserMapper;

import java.util.HashSet;
import java.util.Set;

@Repository
public class JdbcTemplateUserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        String sqlQuery = "SELECT * FROM \"user\" WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new UserMapper(), id);
    }

    @Override
    public User findByUsername(String username) {
        String sqlQuery = "SELECT * FROM \"user\" WHERE username = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new UserMapper(), username);
    }

    @Override
    public void save(User user) {
        String sqlInsertProductQuery = "INSERT INTO \"user\" (id, username, password) VALUES (?,?,?)";
        String sqlInsertRoleQuery = "INSERT INTO user_role(user_id, role_id) VALUES (?, ?)";

        jdbcTemplate.update(sqlInsertProductQuery, user.getId(), user.getUsername(), user.getPassword());
        jdbcTemplate.update(sqlInsertRoleQuery, user.getId(), UserRole.ROLE_USER.getRoleId());

    }

    @Override
    public void delete(Long id) {
        String sqlQuery = "DELETE FROM \"user\" WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public Set<User> findAll() {
        String sqlQuery = "SELECT * FROM \"user\"";
        return new HashSet<>(jdbcTemplate.query(sqlQuery, new UserMapper()));
    }
}
