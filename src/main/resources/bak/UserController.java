package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.RoleService;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final DiscountCardService discountCardService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, DiscountCardService discountCardService) {
        this.userService = userService;
        this.roleService = roleService;
        this.discountCardService = discountCardService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "account/loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage() {
        return "account/logoutSuccessfulPage";
    }

    @GetMapping("/info")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName()).orElseThrow();
        List<Role> userRoles = roleService.findAllByUserId(user.getId());
        model.addAttribute("userRoles", userRoles);
        return "account/infoPage";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model, @ModelAttribute(value = "usernameError") String usernameError) {
        List<DiscountCard> discountCards = discountCardService.findAll();
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("discountCards", discountCards);
        model.addAttribute("usernameError", model.getAttribute("usernameError"));

        return "account/registrationPage";
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.
    }

}
