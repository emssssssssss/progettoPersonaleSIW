package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.model.Fascia;
import it.uniroma3.service.FasciaService;
import it.uniroma3.model.Utente;
import it.uniroma3.service.UtenteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class FasciaController {

    @Autowired 
    private FasciaService fasciaService;

    @Autowired
    private UtenteService utenteService;

    //visualizza tutte le fasce
    @GetMapping("/staff/fasce")
    public String getAllFasce(Model model) {
        model.addAttribute("fasce", this.fasciaService.getAllFascia());
         return "fasceStaff";
    }
    
    //mostra form per creare nuova fascia
    @GetMapping("/staff/fasce/nuova")
    public String showCreateForm(Model model) {
        model.addAttribute("fascia", new Fascia());
        return "formFascia";
    }

    //salva nuova fascia
    @PostMapping("/staff/fasce")
    public String creaFascia(@Valid @ModelAttribute Fascia fascia, BindingResult result) {
        if(result.hasErrors()) {
            return "formFascia";
        }

        Utente utenteAutenticato = utenteService.getUtenteAutenticato();
        fasciaService.salvaFascia(fascia, utenteAutenticato);

        return "redirect:/staff/fasce";
    }


    //mostra form per modificare una fascia eszistente
    @GetMapping("/staff/fasce/edit/{id}")
    public String mostraEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("fascia", fasciaService.getFasciaById(id));
        return "formFascia";
    }

    //salva modifiche fascia
    @PostMapping("/staff/fasce/update")
    public String updateFascia(@Valid @ModelAttribute Fascia fascia, BindingResult result) {
        if(result.hasErrors()) {
            return "formFascia";
        }

        Utente utenteAutenticato = utenteService.getUtenteAutenticato();
        fasciaService.salvaFascia(fascia, utenteAutenticato);

        return "redirect:/staff/fasce";
    }


    //elimina fascia
    @GetMapping("/staff/fasce/delete/{id}")
    public String deleteFascia(@PathVariable Long id) {
        Utente utenteAutenticato = utenteService.getUtenteAutenticato();
        fasciaService.cancellaFascia(id, utenteAutenticato);

        return "redirect:/staff/fasce";
    }


    @GetMapping("/fascia/{id}")
    public String getFascia(@PathVariable("id") Long id, Model model) {
        model.addAttribute("fascia", this.fasciaService.getFasciaById(id));
        return "fascia";
    }


    @GetMapping("/fasce")
    public String mostraFasceDisponibili(Model model) {
        model.addAttribute("fasce", fasciaService.getAllFascia());
        return "fasceDisponibili";
    }
    


    
}
