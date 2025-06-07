package it.uniroma3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;



import it.uniroma3.model.Fascia;
import it.uniroma3.model.Utente;
import it.uniroma3.service.FasciaService;
import it.uniroma3.service.PrenotazioneService;
import it.uniroma3.service.UtenteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PrenotazioneController {

    @Autowired 
    private PrenotazioneService prenotazioneService;

    @Autowired 
    private UtenteService utenteService;

    @Autowired 
    private FasciaService fasciaService;

    @GetMapping("/prenotazione/{id}")
    public String getPrenotazione(@PathVariable("id") Long id, Model model) {
        model.addAttribute("prenotazione", this.prenotazioneService.getPrenotazione(id));
        return "Prenotazione";
    }

    @GetMapping("/prenotazioni")
    public String getPrenotazioni(Model model) {
        model.addAttribute("prenotazioni", this.prenotazioneService.getAllPrenotazioni());
        return "Prenotazioni";
    }

    @GetMapping("/prenotazioni/{idFascia}")
    public String getPrenotazioniEvento(@PathVariable("idFascia") Long id, Model model) {
        Fascia fascia = this.fasciaService.getFasciaById(id);
        model.addAttribute("prenotazioni", fascia.getPrenotazioni());
        return "Prenotazioni";
    }

    @GetMapping("/prenotazioni/{idUtente}")
    public String getPrenotazioniUtente(@PathVariable("idUtente") Long id, Model model) {

        //prendo l'utente autenticato
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailLoggato = auth.getName();

        //recupero utente loggato
        Optional<Utente> utenteLoggatoOpt = utenteService.getUtenteByEmail(emailLoggato);

        // Controlla se l’utente loggato sta cercando di accedere alle sue prenotazioni
        if (!utenteLoggato.getId().equals(idUtente)) {
            // Accesso negato: magari reindirizza o mostra errore
            return "accessoNegato";  // o "redirect:/error" o simile
        }

        // Se ok, mostra prenotazioni
        model.addAttribute("prenotazioni", utenteLoggato.getPrenotazioni());
        return "Prenotazioni";        

    }
}
