package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.service.MuseoService;
import it.uniroma3.model.Museo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class MuseoController {

    @Autowired 
    private MuseoService museoService;


    @GetMapping("/")
    public String mostraHomepage(Model model) {
        model.addAttribute("museo", museoService.getMuseoUnico());
        return "homepage";
    }

    //pagina per lo staff per modificare il museo
    @GetMapping("/staff/museo/modifica")
    public String mostraFormModificaMuseo(Model model) {
        model.addAttribute("museo", museoService.getMuseoUnico());
        return "staff/modificaMuseo";
    }

    //salva modifiche
    @PostMapping("/staff/museo/modifica")
    public String aggiornaMuseo(@ModelAttribute("museo") Museo museo, Model model) {
        museoService.aggiornaMuseo(museo);
        return "redirect:/staff/museo/modifica?successo";
    }
    
    
    
}
