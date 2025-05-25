package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.service.FasciaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class FasciaController {

    @Autowired FasciaService fasciaService;

    @GetMapping("/fascia/{id}")
    public String getFascia(@PathVariable("id") Long id, Model model) {
        model.addAttribute("fascia", this.fasciaService.getFasciaById(id));
        return "Fascia";
    }

    @GetMapping("/fasce")
    public String getFasce(Model model) {
        model.addAttribute("fasce", this.fasciaService.getAllFascia());
        return "Fasce";
    }
    
    
}
