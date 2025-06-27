package it.uniroma3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.model.Artista;
import it.uniroma3.model.Utente;
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
        Optional<Artista> artistaOpt = artistaService.getArtistaById(id);
        if (artistaOpt.isPresent()) {
            model.addAttribute("artista", artistaOpt.get());
            return "Artista";
        } else 
            return "redirect:/artisti";  // artista non trovato
    }

    @GetMapping("/artisti")
    public String getArtisti(Model model) {
        model.addAttribute("artisti", artistaService.getAllArtisti());
        return "artisti";
    } 

    // Mostra form per aggiungere nuovo artista
    @GetMapping("/artista/aggiungi")
    public String mostraFormArtista(Model model) {
        model.addAttribute("artista", new Artista());
        return "formArtista";
    }

    @PostMapping("/artista/salva")
public String salvaArtista(@Valid @ModelAttribute Artista artista,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal User currentUser,
                           Model model) {

    if (bindingResult.hasErrors()) {
        model.addAttribute("formError", true);
        return "formArtista"; // Nome del template Thymeleaf unificato per aggiunta/modifica
    }

    Utente utente = utenteService.getUtenteByEmail(currentUser.getUsername())
                    .orElseThrow(() -> new RuntimeException("Utente non trovato"));

    if (artista.getId() == null) {
        artistaService.aggiungiArtista(artista, utente);
    } else {
        artistaService.aggiornaArtista(artista, utente);
    }

    return "redirect:/artista/" + artista.getId();
}


    // Elimina artista
    @PostMapping("/artista/elimina/{id}")
    public String eliminaArtista(@PathVariable Long id,
                                @AuthenticationPrincipal User currentUser) {

        Utente utente = utenteService.getUtenteByEmail(currentUser.getUsername())
                        .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        artistaService.cancellaArtista(id, utente);

        return "redirect:/artisti";
    }

    @GetMapping("/artista/modifica/{id}")
    public String mostraFormModificaArtista(@PathVariable Long id, Model model) {
        Artista artista = artistaService.getArtistaById(id)
            .orElseThrow(() -> new RuntimeException("Artista non trovato"));
        model.addAttribute("artista", artista);
        return "formArtista";
    }

}
