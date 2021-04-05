package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.RoleService;
import ru.clevertec.cashReceiptWeb.security.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping({"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage() {
        return "logoutSuccessfulPage";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
        Set<Role> userRoles = roleService.findAllByUserId(user.getId());
        model.addAttribute("userRoles", userRoles);
        return "userInfoPage";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration";
    }

    @PostMapping("/addUser")
    public String addProduct(@ModelAttribute(value = "user") User user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403Page";
    }
}
