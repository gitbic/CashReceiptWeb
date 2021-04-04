package ru.clevertec.cashReceiptWeb.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.repository.RoleRepository;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Set<Role> findAllByUserId(Long userId) {
        return roleRepository.findAllByUserId(userId);
    }
}
