package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.RoleService;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;

import java.util.List;
import java.util.Set;

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    DiscountCardService discountCardService;

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
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        Set<Role> userRoles = roleService.findAllByUserId(user.getId());
        model.addAttribute("userRoles", userRoles);
        return "userInfoPage";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        List<DiscountCard> discountCards = discountCardService.findAll();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("discountCards", discountCards);
        return "registration";
    }

    @PostMapping("/addUser")
    public String addProduct(@ModelAttribute(value = "user") User user) {
        System.out.println(user);
        userService.save(user);

        return "redirect:/login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403Page";
    }
}
