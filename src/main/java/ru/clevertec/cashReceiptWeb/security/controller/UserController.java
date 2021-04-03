package ru.clevertec.cashReceiptWeb.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.SecurityService;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.security.validator.UserValidator;

import java.security.Principal;

@Controller
//@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    private final static String AUTH_DIR = "auth/";

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping({ "/", "/welcome" })
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

//        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", loginedUser.toString());
        return "adminPage";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, Principal principal) {

//        String userName = principal.getName();
//
//        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

//        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", loginedUser.toString());

        return "userInfoPage";
    }

    @GetMapping("/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

//            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", loginedUser.toString());

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }




//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(userForm);
//        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
//        return "redirect:/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null) {
//            model.addAttribute("error", "Username or password  is invalid");
//        }
//
//        if (logout != null) {
//            model.addAttribute("message", "You have been logged out successfully");
//        }
//
//        return "login";
//    }
//
//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
}
