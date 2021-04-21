package ru.clevertec.cashReceiptWeb.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.repository.RoleRepository;
import ru.clevertec.cashReceiptWeb.security.service.RoleService;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRolesByUserId(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getAllRolesByUserId", userId);

        List<Role> userRoleList = roleRepository.findAllByUserId(userId);

        log.info("Method: {}, output value: {}", "getAllRolesByUserId", userRoleList);
        return userRoleList;
    }
}
