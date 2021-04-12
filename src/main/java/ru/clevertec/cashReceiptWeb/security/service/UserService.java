package ru.clevertec.cashReceiptWeb.security.service;

import ru.clevertec.cashReceiptWeb.dto.UserRequestDto;
import ru.clevertec.cashReceiptWeb.dto.UserResponseDto;
import ru.clevertec.cashReceiptWeb.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);

    UserResponseDto getUserResponseDto(Long id);

    Optional<User> findUserByUserName(String username);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    UserResponseDto addUser(UserRequestDto userRequestDto);

    User saveUser(User user);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    List<UserResponseDto> getAllUsersResponseDto();

    User getCurrentUser();
}
