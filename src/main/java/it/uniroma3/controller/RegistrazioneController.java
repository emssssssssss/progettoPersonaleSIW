package it.uniroma3.controller;

import it.uniroma3.model.Utente;
import it.uniroma3.service.UtenteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    // Mostra il modulo di registrazione
    @GetMapping("/Registrazione")
    public String mostraRegistrazione(Model model) {
        model.addAttribute("utente", new Utente());
        return "Registrazione";
    }

    // Gestisce l'invio del form
    @PostMapping("/Registrazione")
    public String registraUtente(
            @Valid @ModelAttribute("utente") Utente utente,
            BindingResult errors,
            @RequestParam String passwordBis,
            @RequestParam(required = false) String codiceAmministratore,
            Model model) {

        // Controlla errori di validazione nei campi base
        if (errors.hasErrors()) {
            return "Registrazione";
        }

        // Controllo manuale della conferma password
        if (!utente.getPassword().equals(passwordBis)) {
            model.addAttribute("errore", "Le password non coincidono");
            return "Registrazione";
        }

        // Verifica se l'email è già in uso
        if (utenteService.getUtenteByEmail(utente.getEmail()) != null) {
            model.addAttribute("errore", "Email già registrata");
            return "Registrazione";
        }

        // Assegna ruolo in base al codice amministratore
        if (utenteService.permessoAdmin(codiceAmministratore)) {
            utente.setRuolo(Utente.Ruolo.STAFF);
        } else {
            utente.setRuolo(Utente.Ruolo.VISITATORE);
        }

        // Salva utente nel database
        try {
            utenteService.addUtente(utente);
        } catch (RuntimeException e) {
            model.addAttribute("errore", "Errore durante la registrazione: " + e.getMessage());
            return "Registrazione";
        }

        // Reindirizza al login con messaggio di successo
        return "redirect:/login?Registrazione=successo";
    }
}
