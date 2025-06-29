package it.uniroma3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.model.Evento;
import it.uniroma3.model.Opera;
import it.uniroma3.model.Utente;
import it.uniroma3.service.ArtistaService;
import it.uniroma3.service.EventoService;
import it.uniroma3.service.MuseoService;
import it.uniroma3.service.OperaService;
import it.uniroma3.service.UtenteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class OperaController {

    @Autowired 
    private OperaService operaService;

    @Autowired 
    private EventoService eventoService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private MuseoService museoService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/opera/{id}")
    public String getOpera(@PathVariable Long id, Model model) {
        Optional<Opera> operaOpt = operaService.getOperaById(id);
        if (operaOpt.isPresent()) {
            model.addAttribute("opera", operaOpt.get());
            return "Opera";
        } else {
            // Puoi gestire l’errore come preferisci: redirect, pagina di errore, messaggio, ecc.
            return "redirect:/Opere"; // ad esempio torna alla lista opere
        }
    }

    @GetMapping("/opere")
    public String getOpere(Model model) {
        model.addAttribute("opere", this.operaService.getAllOpere());
        return "Opere";
    }

    @GetMapping("/opere/{idEvento}")
    public String getOpereEvento(@PathVariable("idEvento") Long id, Model model) {
        Evento evento = this.eventoService.getEventoById(id);
        model.addAttribute("evento", evento);
        model.addAttribute("opere", evento.getOpere());
        return "Opere";
    }
    

    @GetMapping("/opera/aggiungi")
    public String formAggiungiOpera(Model model) {
        model.addAttribute("opera", new Opera());
        model.addAttribute("museoGestito", museoService.getMuseoUnico());
        model.addAttribute("artisti", artistaService.getAllArtisti());

        return "formOpera"; 
    }

    @PostMapping("/opera/aggiungi")
    public String aggiungiOpera(@ModelAttribute("opera") @Valid Opera opera,
                                @AuthenticationPrincipal User currentUser) {
        opera.setMuseo(museoService.getMuseoUnico());
        operaService.aggiungiOpera(opera);
        return "redirect:/opere";
    }

    @GetMapping("/opera/modifica/{id}")
    public String formModificaOpera(@PathVariable Long id, Model model) {
        Optional<Opera> operaOpt = operaService.getOperaById(id);
        if (operaOpt.isPresent()) {
            model.addAttribute("opera", operaOpt.get());
            // Aggiungi anche artisti e museo se li usi nel form
            model.addAttribute("artisti", artistaService.getAllArtisti());
            model.addAttribute("museoGestito", museoService.getMuseoUnico());
            return "formOpera"; 
        } else {
            return "redirect:/opere";
        }
    }

    @PostMapping("/opera/modifica/{id}")
    public String modificaOpera(@PathVariable Long id,
                                 @ModelAttribute("opera") @Valid Opera opera,
                                 @AuthenticationPrincipal User currentUser) {
        Optional<Utente> utente = utenteService.getUtenteByEmail(currentUser.getUsername());
        opera.setId(id); 
        operaService.modificaOpera(opera, utente);
        return "redirect:/opera/" + id;
    }

    @PostMapping("/opera/elimina/{id}")
    public String eliminaOpera(@PathVariable Long id,
                                @AuthenticationPrincipal User currentUser) {
        Optional<Utente> utente = utenteService.getUtenteByEmail(currentUser.getUsername());
        operaService.cancellaOpera(id, utente);
        return "redirect:/opere";
    }
    
}
