package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.model.Evento;
import it.uniroma3.service.EventoService;
import it.uniroma3.service.OperaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
public class OperaController {

    @Autowired 
    private OperaService operaService;

    @Autowired 
    private EventoService eventoService;

    @GetMapping("/opera/{id}")
    public String getOpera(@PathVariable Long id, Model model) {
        model.addAttribute("opera", this.operaService.getOperaById(id));
        return "Opera";
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
    
    
    
}
