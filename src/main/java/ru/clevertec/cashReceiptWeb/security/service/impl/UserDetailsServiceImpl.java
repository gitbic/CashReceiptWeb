package ru.clevertec.cashReceiptWeb.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(userName);

        if (optionalUser.isEmpty()) {
            log.warn("User not found {}", userName);
            throw new UsernameNotFoundException("User " + userName + " not found in the database");
        }

        log.info("Found user {}", userName);
        User user = optionalUser.get();

        List<Role> roles = roleRepository.findAllByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (roles != null) {
            for (Role role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}