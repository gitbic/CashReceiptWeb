package ru.clevertec.cashReceiptWeb.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.constants.GlobalConst;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        log.info("Method: {}, input value: {}", "addUser", userRequestDto);

        User user = modelMapper.map(userRequestDto, User.class);

        String username = user.getUsername();

        if (userRepository.existsByUsername(username)) {
            log.warn("Method: {}, username {} is already exist", "addUser", username);
            throw new UsernameExistException(username);
        }

        if (user.getCardNumber() == null) {
            user.setCardNumber(GlobalConst.DISCOUNT_CARD_NUMBER_NONE);
        }

        user = saveUser(user);

        UserRole roleUser = UserRole.ROLE_USER;
        roleRepository.saveUserRole(user.getId(), roleUser.getRoleId());
        log.info("Method: {}, userRole save as: {}", "addUser", roleUser);

        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

        log.info("Method: {}, output value: {}", "addUser", userResponseDto);
        return userResponseDto;
    }


    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        log.info("Method: {}, input values: id = {}, {}", "updateUser", id, userRequestDto);

        User newUser = modelMapper.map(userRequestDto, User.class);

        User user = getUserById(id).toBuilder()
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .cardNumber(newUser.getCardNumber())
                .build();

        user = saveUser(user);

        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

        log.info("Method: {}, output value: {}", "updateUser", userResponseDto);
        return userResponseDto;
    }


    @Override
    public User saveUser(User user) {
        log.info("Method: {}, input value: {}", "saveUser", user);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        log.info("Method: {}, output value: {}", "saveUser", savedUser);
        return savedUser;
    }


    @Override
    public User getUserById(Long id) {
        log.info("Method: {}, input value: id = {}", "getUserById", id);

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        log.info("Method: {}, output value: {}", "getUserById", user);
        return user;
    }


    @Override
    public void deleteUserById(Long id) {
        log.info("Method: {}, input value: id = {}", "deleteUserById", id);

        userRepository.deleteById(id);

        log.info("Method: {}, output value: {}", "deleteUserById", "none");
    }


    @Override
    public UserResponseDto getUserResponseDto(Long id) {
        log.info("Method: {}, input value: id = {}", "getUserResponseDto", id);

        User user = getUserById(id);
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

        log.info("Method: {}, output value: {}", "getUserResponseDto", userResponseDto);
        return userResponseDto;
    }


    @Override
    public List<UserResponseDto> getAllUsersResponseDto() {
        log.info("Method: {}, input value: {}", "getAllUsersResponseDto", "none");

        List<UserResponseDto> userResponseDtoList = userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());

        log.info("Method: {}, output value: {}", "getAllUsersResponseDto", userResponseDtoList);
        return userResponseDtoList;
    }

}
