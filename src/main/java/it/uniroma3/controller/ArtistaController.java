package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.model.Artista;
import it.uniroma3.service.ArtistaService;
import it.uniroma3.service.UtenteService;
import jakarta.validation.Valid;


@Controller
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private UtenteService utenteService;


    @GetMapping("/artista/{id}")
    public String getArtista(@PathVariable Long id, Model model) {
        model.addAttribute("artista", this.artistaService.getArtistaById(id));
        return "Artista";
    }

    @GetMapping("/artisti")
    public String getArtisti(Model model) {
        model.addAttribute("artisti", this.artistaService.getAllArtisti());
        return "artisti";
    }


    // Mostra form per aggiungere nuovo artista
    @GetMapping("/artista/aggiungi")
    public String mostraFormArtista(Model model) {
        model.addAttribute("artista", new Artista());
        return "formArtista"; 
    }


        // Gestisce submit form nuovo artista
    @PostMapping("/artista/aggiungi")
    public String aggiungiArtista(
        @Valid @ModelAttribute Artista artista,
        BindingResult bindingResult,
        Model model) {

             // Controllo errori di validazione 
            if (bindingResult.hasErrors()) {
                return "formArtista"; 
            }


        //VA COMPLETATO ma serve prima integrare l autenticazione

        return "redirect:/artista/" + artista.getId();
        }

    
    
}
