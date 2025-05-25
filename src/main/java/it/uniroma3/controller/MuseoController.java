package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.service.MuseoService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MuseoController {

    @Autowired MuseoService museoService;

    @GetMapping("/")
    public String getMuseo(Model model) {
        model.addAttribute("museo", this.museoService.getMuseo((long) 1));
        return "Home";
    }
    
}
