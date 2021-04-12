package ru.clevertec.cashReceiptWeb.security.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.UserRequestDto;
import ru.clevertec.cashReceiptWeb.dto.UserResponseDto;
import ru.clevertec.cashReceiptWeb.exception.UserNotFoundException;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;
import ru.clevertec.cashReceiptWeb.security.repository.enums.UserRole;
import ru.clevertec.cashReceiptWeb.security.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addUser(User user) {
        boolean isUserAdded = false;

        if (findUserByUserName(user.getUsername()).isEmpty()) {
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
        return saveUser(newUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        user.setId(id);
        user = saveUser(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = saveUser(modelMapper.map(userRequestDto, User.class));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public Optional<User> findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return findUserByUserName(authentication.getName()).orElseThrow();
        return findUserById(1L).orElseThrow();
    }

    @Override
    public UserResponseDto getUserResponseDto(Long id) {
        User user = findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsersDto() {
        return findAllUsers().stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }
}
