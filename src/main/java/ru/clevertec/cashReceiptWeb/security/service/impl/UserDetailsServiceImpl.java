package ru.clevertec.cashReceiptWeb.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.constants.ErrMsg;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.repository.RoleRepository;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Method: {}, input value: username = {}", "loadUserByUsername", username);

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            log.warn("Method: {}, username {} not found", "loadUserByUsername", username);
            throw new UsernameNotFoundException(username + ErrMsg.USERNAME_NOT_FOUND);
        }

        User user = optionalUser.get();

        List<Role> roles = roleRepository.findAllByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (roles != null) {
            for (Role role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }

        org.springframework.security.core.userdetails.User userDetail =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), grantedAuthorities);

        log.info("Method: {}, output value: {}", "loadUserByUsername", userDetail);
        return userDetail;
    }

}