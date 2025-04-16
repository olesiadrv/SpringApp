package com.example.spring_pr3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Невірний логін або пароль");
        }
        return "login";  // Це назва вашого Thymeleaf шаблону (login.html)
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        // Перевірка ролі користувача та перенаправлення на відповідну сторінку
        if (isUserAdmin()) {
            return "redirect:/admin-panel";  // Переадресація для адміністраторів
        } else {
            return "redirect:/students";  // Переадресація для звичайних користувачів
        }
    }

    // Метод для перевірки, чи користувач є адміністратором
    private boolean isUserAdmin() {
        // Для цього можна отримати роль користувача з контексту
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    // Новий метод для адмін-панелі
    @GetMapping("/admin-panel")
    public String showAdminPanel() {
        return "admin-panel";  // Це шаблон адмін-панелі
    }
}


