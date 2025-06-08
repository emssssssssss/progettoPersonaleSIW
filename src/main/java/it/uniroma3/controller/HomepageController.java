package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;

//import it.uniroma3.model.Utente;
import it.uniroma3.service.MuseoService;
import it.uniroma3.service.UtenteService;

@Controller
public class HomepageController {

    @Autowired 
    private UtenteService utenteService;

    @Autowired 
    private MuseoService museoService;

    @GetMapping("/homepage")
    public String mostraHomepage(Model model, Authentication auth) {
        model.addAttribute("museo", museoService.getMuseoUnico());

        if(auth != null && auth.isAuthenticated()){
            String email = auth.getName();
            utenteService.getUtenteByEmail(email).ifPresent(utente -> model.addAttribute("utente", utente));
        }
        return "homepage";  // pagina home pubblica
    }


    
}
