package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.service.ArtistaService;


@Controller
public class ArtistaController {

    @Autowired
    ArtistaService artisaService;

    @GetMapping("/artista/{id}")
    public String getArtista(@PathVariable("id") Long id, Model model) {
        model.addAttribute("artista", this.artisaService.getArtistaById(id));
        return "Artista";
    }

    @GetMapping("/artisti")
    public String geArtisti(Model model) {
        model.addAttribute("artisti", this.artisaService.getAllArtisti());
        return "Artisti";
    }
    
}
