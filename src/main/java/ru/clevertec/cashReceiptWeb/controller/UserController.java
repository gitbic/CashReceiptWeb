package ru.clevertec.cashReceiptWeb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.UserRequestDto;
import ru.clevertec.cashReceiptWeb.dto.UserResponseDto;
import ru.clevertec.cashReceiptWeb.security.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Method: {}, input value: {}", "addUser", userRequestDto);

        UserResponseDto userResponseDto = userService.addUser(userRequestDto);

        log.info("Method: {}, output value: {}", "addUser", userResponseDto);
        return userResponseDto;
    }


    @PutMapping("/{id}")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        log.info("Method: {}, input values: id = {}, {}", "updateUser", id, userRequestDto);

        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);

        log.info("Method: {}, output value: {}", "updateUser", userResponseDto);
        return userResponseDto;
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Method: {}, input value: id = {}", "deleteUser", id);

        userService.deleteUserById(id);

        log.info("Method: {}, output value: id = {}", "deleteUser", "none");
    }


    @GetMapping()
    public List<UserResponseDto> getAllUsersDto() {
        log.info("Method: {}, input value: {}", "getAllUsersDto", "none");

        List<UserResponseDto> userResponseDtoList = userService.getAllUsersResponseDto();

        log.info("Method: {}, output value: {}", "getAllUsersDto", userResponseDtoList);
        return userResponseDtoList;
    }


    @GetMapping("/{id}")
    public UserResponseDto getUserDto(@PathVariable Long id) {
        log.info("Method: {}, input value: id = {}", "getUserDto", id);

        UserResponseDto userResponseDto = userService.getUserResponseDto(id);

        log.info("Method: {}, output value: {}", "getUserDto", userResponseDto);
        return userResponseDto;
    }

}
