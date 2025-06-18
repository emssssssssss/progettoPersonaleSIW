package it.uniroma3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.model.Utente;
import it.uniroma3.service.MuseoService;
import it.uniroma3.service.UtenteService;

@Controller
public class LoginController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private MuseoService museoService;

    @GetMapping("/login")
    public String mostraLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errore", "Email o password non validi");
        }
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("errore", "Accesso fallito");
        return "login";
    }

    @GetMapping("/default")
    public String mostraHomepage(Model model, Authentication authentication) {
        model.addAttribute("museo", museoService.getMuseoUnico());

        String nome = authentication.getName();

        // Prova prima con il nome, poi con la mail se non trovato
        Optional<Utente> optionalUtente = utenteService.getUtenteByName(nome);
        if (optionalUtente.isEmpty()) {
            optionalUtente = utenteService.getUtenteByEmail(nome);
        }

        Utente utente = optionalUtente
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con nome o email: " + nome));

        model.addAttribute("utente", utente);
        return "homepage";
    }

}
