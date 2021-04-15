package ru.clevertec.cashReceiptWeb.security.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.UserRequestDto;
import ru.clevertec.cashReceiptWeb.dto.UserResponseDto;
import ru.clevertec.cashReceiptWeb.exception.UserNotFoundException;
import ru.clevertec.cashReceiptWeb.exception.UsernameExistException;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.repository.RoleRepository;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;
import ru.clevertec.cashReceiptWeb.security.repository.enums.UserRole;
import ru.clevertec.cashReceiptWeb.security.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameExistException(user.getUsername());
        }

        user = saveUser(user);
        roleRepository.saveUserRole(user.getId(), UserRole.ROLE_USER.getRoleId());

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User newUser = modelMapper.map(userRequestDto, User.class);
        User user = findUserById(id);

        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setCardNumber(newUser.getCardNumber());
        user = saveUser(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
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
    public UserResponseDto getUserResponseDto(Long id) {
        User user = findUserById(id);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsersResponseDto() {
        return findAllUsers().stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }
}
