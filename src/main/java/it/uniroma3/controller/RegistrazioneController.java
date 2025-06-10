package it.uniroma3.controller;

import it.uniroma3.model.Utente;
import it.uniroma3.service.UtenteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/registrazione")
    public String mostraRegistrazione(Model model) {
        model.addAttribute("utente", new Utente());
        return "registrazione";
    }

   @PostMapping("/registrazione")
    public String registraUtente(@Valid Utente utente, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "registrazione";
        }
        if (!utente.getPassword().equals(utente.getPasswordBis())) {
            model.addAttribute("errore", "Le password non coincidono");
            return "registrazione";
        }

        try {
            utenteService.addUtente(utente);
        } catch (RuntimeException e) {
            model.addAttribute("errore", e.getMessage());
            return "registrazione";
        }
        return "redirect:/login?registrazione=successo";
    }
}
