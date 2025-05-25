package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.service.UtenteService;


@Controller
public class UtenteController {

    @Autowired UtenteService utenteService;

    @GetMapping("/utente/{id}")
    public String getUtente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("utente", this.utenteService.getUtenteById(id));
        return "Utente";
    }

    @GetMapping("/utenti")
    public String getUtenti(Model model) {
        model.addAttribute("utenti", this.utenteService.getAllUtenti());
        return "Utenti";
    }
    
    
}
