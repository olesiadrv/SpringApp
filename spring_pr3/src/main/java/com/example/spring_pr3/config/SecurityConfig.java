package com.example.spring_pr3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home").permitAll()  // головна доступна всім
                        .requestMatchers("/students/**", "/api/**").authenticated() // захищені
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults()) // дефолтна форма логіну
                .httpBasic(Customizer.withDefaults()) // basic auth (для Postman)
                .csrf(csrf -> csrf.disable()) // вимикаємо CSRF для простоти
                .build();
    }
}
