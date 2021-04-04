package ru.clevertec.cashReceiptWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminCOntroller {

    @GetMapping
    public String adminPage(Model model) {
        return "admin/adminPage";
    }
}
