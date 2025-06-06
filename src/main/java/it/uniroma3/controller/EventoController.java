package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;

//import it.uniroma3.model.Evento;
//import it.uniroma3.model.Utente;
//import it.uniroma3.model.Utente;
import it.uniroma3.service.EventoService;
import it.uniroma3.service.UtenteService;
//import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class EventoController {

    @Autowired 
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    //visualizza un singolo evento
    @GetMapping("/evento/{id}")
    public String getEvento(@PathVariable("id") Long id, Model model) {
        model.addAttribute("evento", this.eventoService.getEventoById(id));
        return "Evento";
    }

    //visualizza la lista di eventi
    @GetMapping("/eventi")
    public String getEventi(Model model) {
        model.addAttribute("eventi", this.eventoService.getAllEventi());
        return "Eventi";
    }

    /* 
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
        */
    

    /*   NON SO SE VA RICONTROLLARE 
    //gestione oer aggiungere  un evento
    @PostMapping("/evento/aggiungi")
    public String aggiungiEvento(
        @Valid @ModelAttribute("evento") Evento evento,
        BindingResult bindingResult,
        Model model) {

        if (bindingResult.hasErrors()) {
            return "formEvento";
        }

        eventoService.aggiungiEvento(evento, null); 
        return "redirect:/evento/" + evento.getId();
    }   */ 

    /* 
    //mostra form di modifica di un evento esistente
    @GetMapping("/evento/modifica/{id}")
    public String mostraFormModificaEvento(@PathVariable("id") Long id, Model model) {
        Utente utente = utenteService.getUtenteAutenticato();
        if (!utenteService.isAdmin(utente)) {
            throw new RuntimeException("Accesso negato: solo lo staff può modificare eventi.");
        }
        Evento evento = eventoService.getEventoById(id);
        model.addAttribute("evento", evento);
        return "formEvento";
    }
        
    */

    /*  UGUALE NON SO SE VA
    //gestione della modifica
    @PostMapping("/evento/modifica/{id}")
    public String modificaEvento(
        @PathVariable("id") Long id,
        @Valid @ModelAttribute("evento") Evento evento,
        BindingResult bindingResult,
        Model model) {

        if (bindingResult.hasErrors()) {
            return "formEvento";
        }

        evento.setId(id); 
        eventoService.modificaEvento(evento, null); 

        return "redirect:/evento/" + evento.getId();
    } */


    /* PENSO CHE SE NON VANNO I DUE SOPRA VADA SOLO QUESTO
    // Salva nuovo o modificato evento (solo staff)
    @PostMapping("/evento/salva")
    public String salvaEvento(@Valid @ModelAttribute("evento") Evento evento,
                              BindingResult bindingResult,
                              Model model) {
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
    } */


    /* VA SISTEMATO UNA VOLTA AGGIORNATA L AUTENTICAZIONE
     // Salva nuovo o modificato evento (solo staff)
    @PostMapping("/evento/salva")
    public String salvaEvento(@Valid @ModelAttribute("evento") Evento evento,
                              BindingResult bindingResult,
                              Model model) {
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
 */

}
