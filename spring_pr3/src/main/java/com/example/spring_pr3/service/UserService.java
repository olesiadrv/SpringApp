package com.example.spring_pr3.service;

import com.example.spring_pr3.dto.UserRegistrationDTO;
import com.example.spring_pr3.model.Role;
import com.example.spring_pr3.model.User;
import com.example.spring_pr3.repository.RoleRepository;
import com.example.spring_pr3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Отримуємо роль за ім'ям з DTO
        String roleName = dto.getSelectedRole();
        Optional<Role> roleOptional = roleRepository.findByName(roleName);

        Role role;
        if (roleOptional.isEmpty()) {
            // Якщо роль не знайдена, створюємо нову
            role = new Role(roleName);
            roleRepository.save(role);  // Зберігаємо нову роль у базі даних
        } else {
            role = roleOptional.get();  // Якщо роль знайдена, використовуємо її
        }

        // Додаємо роль до користувача
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }
}
