package ru.clevertec.cashReceiptWeb.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.security.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT r.id, r.role_name FROM role r, user_role ur WHERE r.id = ur.role_id and ur.user_id = ?",
    nativeQuery = true)
    List<Role> findAllByUserId(Long userId);


}
