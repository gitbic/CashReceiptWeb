package ru.clevertec.cashReceiptWeb.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.repository.RoleRepository;
import ru.clevertec.cashReceiptWeb.security.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAllByUserId(Long userId) {
        return roleRepository.findAllByUserId(userId);
    }
}
