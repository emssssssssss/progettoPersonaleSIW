package it.uniroma3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.model.Evento;
import it.uniroma3.model.Utente;
import it.uniroma3.service.EventoService;
import it.uniroma3.service.UtenteService;
import it.uniroma3.repository.EventoRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class EventoController {

    @Autowired 
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EventoRepository eventoRepository;

    //visualizza un singolo evento
    @GetMapping("/evento/{id}")
    public String getEvento(@PathVariable Long id, Model model) {
        model.addAttribute("evento", this.eventoService.getEventoById(id));
        return "Evento";
    }

    //visualizza la lista di eventi
    @GetMapping("/eventi")
    public String getEventi(Model model) {
        List<Evento> eventi = eventoRepository.findAll();
        model.addAttribute("eventi", eventi);
        return "Eventi";
    }


    //mostra il form per aggiungere un nuovo evento
    @GetMapping("/evento/aggiungi")
    public String mostraFormEvento(Model model) {
        Utente utente = utenteService.getUtenteAutenticato();
        if (!utenteService.isAdmin(utente)) {
            throw new RuntimeException("Accesso negato: solo lo staff può aggiungere eventi.");
        }
        model.addAttribute("evento", new Evento());
        return "formEvento";
    }

    
  
    //mostra form di modifica di un evento esistente
    @GetMapping("/evento/modifica/{id}")
    public String mostraFormModificaEvento(@PathVariable Long id, Model model) {
        Utente utente = utenteService.getUtenteAutenticato();
        if (!utenteService.isAdmin(utente)) {
            throw new RuntimeException("Accesso negato: solo lo staff può modificare eventi.");
        }
        Evento evento = eventoService.getEventoById(id);
        model.addAttribute("evento", evento);
        return "formEvento";
    }

        
    //gestione della modifica
    @PostMapping("/evento/salva")
    public String salvaEvento(@Valid @ModelAttribute Evento evento,
                            BindingResult bindingResult, Model model) {
        Utente utente = utenteService.getUtenteAutenticato();
        if (!utenteService.isAdmin(utente)) {
            throw new RuntimeException("Accesso negato: solo lo staff può salvare eventi.");
        }
        if (bindingResult.hasErrors()) {
            return "formEvento";
        }
        if (evento.getId() == null) {
            eventoService.aggiungiEvento(evento, utente);
        } else {
            eventoService.modificaEvento(evento, utente);
        }
        return "redirect:/evento/" + evento.getId();
    }

    //eliminazione
    @PostMapping("/evento/elimina/{id}")
    public String cancellaEvento(@PathVariable Long id) {
        Utente utente = utenteService.getUtenteAutenticato();
        if(!utenteService.isAdmin(utente)){
            throw new RuntimeException("Accesso Negato");
        }

        eventoService.cancellaEvento(id, utente);
        return "redirect:/eventi";
    }

}