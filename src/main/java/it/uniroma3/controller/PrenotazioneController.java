package it.uniroma3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;



import it.uniroma3.model.Fascia;
import it.uniroma3.model.Utente;
import it.uniroma3.model.Prenotazione;
import it.uniroma3.service.FasciaService;
import it.uniroma3.service.PrenotazioneService;
import it.uniroma3.service.UtenteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PrenotazioneController {

    @Autowired 
    private PrenotazioneService prenotazioneService;

    @Autowired 
    private UtenteService utenteService;

    @Autowired 
    private FasciaService fasciaService;

    @GetMapping("/prenotazione/{id}")
    public String getPrenotazione(@PathVariable Long id, Model model) {
        model.addAttribute("prenotazione", this.prenotazioneService.getPrenotazione(id));
        return "prenotazione";
    }

    @GetMapping("/prenotazioni")
    public String getPrenotazioni(Model model) {
        model.addAttribute("prenotazioni", this.prenotazioneService.getAllPrenotazioni());
        return "prenotazioni";
    }

    @GetMapping("/prenotazioni/{idFascia}")
    public String getPrenotazioniEvento(@PathVariable("idFascia") Long id, Model model) {
        Fascia fascia = this.fasciaService.getFasciaById(id);
        model.addAttribute("prenotazioni", fascia.getPrenotazioni());
        return "prenotazioni";
    }

    @GetMapping("/prenotazioni/{idUtente}")
    public String getPrenotazioniUtente(@PathVariable Long idUtente, Model model) {

        //prendo l'utente autenticato
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailLoggato = auth.getName();

        //recupero utente loggato
        Optional<Utente> utenteLoggatoOpt = utenteService.getUtenteByEmail(emailLoggato);

        if (utenteLoggatoOpt.isEmpty()) {
            return "redirect:/login";
        }

        Utente utenteLoggato = utenteLoggatoOpt.get();

        //controlla che l'utente stia accedendo solo alle sue prenotazioni
        if(!utenteLoggato.getId().equals(idUtente)){
            return "accessoNegato";
        }

        // Se ok, mostra prenotazioni
        model.addAttribute("prenotazioni", utenteLoggato.getPrenotazioni());
        return "prenotazioni";        

    }

    @PreAuthorize("hasAuthority('VISITATORE')")
    @GetMapping("/prenotazioni/nuova")
    public String mostraFormPrenotazione(Model model) {
        model.addAttribute("prenotazione", new Prenotazione());
        model.addAttribute("fasce", fasciaService.getAllFascia());
        return "formPrenotazione";
    }

    @PreAuthorize("hasAuthority('VISITATORE')")
    @PostMapping("/prenotazioni")
    public String salvaPrenotazione(@ModelAttribute Prenotazione prenotazione) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // l'email è lo username
        Utente utente = utenteService.getUtenteByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        prenotazione.setUtente(utente);
        prenotazione.setStato(Prenotazione.Stato.CONFERMATA);

        prenotazioneService.aggiungiPrenotazione(prenotazione);

        return "redirect:/prenotazioni";
    }


    
}
