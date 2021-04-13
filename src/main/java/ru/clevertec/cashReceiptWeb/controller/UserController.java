package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.UserRequestDto;
import ru.clevertec.cashReceiptWeb.dto.UserResponseDto;
import ru.clevertec.cashReceiptWeb.security.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.addUser(userRequestDto);
    }

    //  if user not exist ? addUser : error ???
    // check unique name before update
    @PutMapping("/{id}")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping()
    public List<UserResponseDto> getAllUsersDto() {
        return userService.getAllUsersResponseDto();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserDto(@PathVariable Long id) {
        return userService.getUserResponseDto(id);
    }

}
