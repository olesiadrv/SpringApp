package com.example.spring_pr3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    // Тепер цей конструктор буде автоматично згенерований анотацією @AllArgsConstructor
    // public Role(Long id, String name) {
    //     this.id = id;
    //     this.name = name;
    // }

    // Якщо потрібно конструктор тільки з іменем:
    public Role(String name) {
        this.name = name;
    }

    // геттери та сеттери автоматично генеруються за допомогою анотації @Data
}
