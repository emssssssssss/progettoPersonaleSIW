package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
        Utente utente = this.utenteService.getUtenteById(id);
        model.addAttribute("prenotazioni", utente.getPrenotazioni());
        return "Prenotazioni";
    }
    
    
    
    
}
