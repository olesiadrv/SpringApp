package com.example.spring_pr3.repository;

import com.example.spring_pr3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);  // Метод для пошуку ролі за ім'ям
}
