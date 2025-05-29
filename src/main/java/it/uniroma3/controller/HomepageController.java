package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.service.UtenteService;

public class HomepageController {

    @Autowired UtenteService utenteService;
    
    @GetMapping("/homepage/{utente}")
    public String mostraHomepage(@PathVariable("id") Long id, Model model){
        model.addAttribute("utente", this.utenteService.getUtenteById(id));
        return "homepage";
    }
}
