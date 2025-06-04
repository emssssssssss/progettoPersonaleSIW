package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.service.MuseoService;
import it.uniroma3.service.UtenteService;

@Controller
public class HomepageController {

    @Autowired UtenteService utenteService;
    @Autowired MuseoService museoService;
    
    @GetMapping("/homepage/{utente}")
    public String mostraHomepage(@PathVariable("utente") Long id, Model model){
        Long idMuseo = (long) 1;
        model.addAttribute("museo", this.museoService.getMuseo(idMuseo));
        var utente = utenteService.getUtenteById(id);
        if (utente == null) {
            // Utente non trovato: magari fai redirect alla home pubblica
            return "redirect:/homepage";
        }
        model.addAttribute("utente", utente);
        return "homepage";  // pagina home per utente loggato
    }

    @GetMapping("/homepage")
    public String mostraHomepagePubblica(Model model) {
        Long idMuseo = (long) 1;
        model.addAttribute("museo", this.museoService.getMuseo(idMuseo));
        return "homepage";  // pagina home pubblica
    }


    
}
