package com.example.spring_pr3.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String email;
    private String password;
    private String selectedRole; // Роль користувача (наприклад, "ROLE_USER" або "ROLE_ADMIN")
}
