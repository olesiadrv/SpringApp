package com.example.spring_pr3.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationEventListener {

    @EventListener
    public void handleSuccess(AuthenticationSuccessEvent event) {
        log.info("Успішний вхід: " + event.getAuthentication().getName());
    }

    @EventListener
    public void handleFailure(AbstractAuthenticationFailureEvent event) {
        log.warn("Неуспішний вхід: " + event.getAuthentication().getName());
    }

    @EventListener
    public void handleLogout(org.springframework.security.authentication.event.LogoutSuccessEvent event) {
        log.info("Користувач вийшов: " + event.getAuthentication().getName());
    }
}
