package ru.clevertec.cashReceiptWeb.security.repository.jdbcTemplateImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;
import ru.clevertec.cashReceiptWeb.security.repository.enums.UserRole;
import ru.clevertec.cashReceiptWeb.security.repository.mapper.UserMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class JdbcTemplateUserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplateRoleRepositoryImpl jdbcTemplateRoleRepository;

    public JdbcTemplateUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        String sqlSelectUserQuery = "SELECT * FROM user WHERE id = ?";
        String sqlSelectRoleQuery = "SELECT role_id FROM user_role WHERE user_id = ?";

        User user = jdbcTemplate.queryForObject(sqlSelectUserQuery, new UserMapper(), id);
        List<Long> roleIds = jdbcTemplate.queryForList(sqlSelectRoleQuery, Long.class, id);
        Set<Role> roles = new HashSet<>();

        for (Long roleId : roleIds) {
            roles.add(jdbcTemplateRoleRepository.findById(roleId));
        }

        user.setRoles(roles);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void save(User user) {
        String sqlInsertProductQuery = "INSERT INTO user (id, username, password, password_confirm) VALUES (?,?,?,?)";
        String sqlInsertRoleQuery = "INSERT INTO user_role(user_id, role_id) VALUES (?, ?)";

        jdbcTemplate.update(sqlInsertProductQuery, user.getId(), user.getUsername(), user.getPassword(), user.getPasswordConfirm());

        if (user.getRoles() == null) {
            jdbcTemplate.update(sqlInsertRoleQuery, user.getId(), UserRole.USER.getRoleId());
        } else {
            for (Role role : user.getRoles()) {
                jdbcTemplate.update(sqlInsertRoleQuery, user.getId(), role.getId());
            }
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Set<User> findAll() {
        return null;
    }
}
