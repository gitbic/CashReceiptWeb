package ru.clevertec.cashReceiptWeb.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;
import ru.clevertec.cashReceiptWeb.security.repository.enums.UserRole;
import ru.clevertec.cashReceiptWeb.security.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean add(User user) {
        boolean isUserAdded = false;

        if (findByUserName(user.getUsername()).isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);

            userRepository.saveUserRole(savedUser.getId(), UserRole.ROLE_USER.getRoleId());
            isUserAdded = true;
        }

        return isUserAdded;
    }

    @Override
    public User updateUser(Long id, User newUser) {
        newUser.setId(id);
        return save(newUser);
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
