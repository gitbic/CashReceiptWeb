package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.clevertec.cashReceiptWeb.constants.ErrMsg;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.security.model.Role;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.RoleService;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    DiscountCardService discountCardService;

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
        User user = userService.findByUserName(authentication.getName());
        Set<Role> userRoles = roleService.findAllByUserId(user.getId());
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

    @PostMapping("/add")
    public String addUser(@ModelAttribute(value = "user") User user, RedirectAttributes redirectAttributes) {

        if (!userService.add(user)) {
            redirectAttributes.addAttribute("usernameError", user.getUsername() + ErrMsg.USERNAME_EXIST);
            return "redirect:/account/registration";
        }

        return "account/registrationSuccessfulPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/userManager";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403Page";
    }
}
