package it.uniroma3.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Authenticated {

    public Utente get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Utente) {
            return (Utente) principal;
        }

        // Se usi UserDetails o altro tipo, fai il cast o estrazione qui
        return null;
    }
}

