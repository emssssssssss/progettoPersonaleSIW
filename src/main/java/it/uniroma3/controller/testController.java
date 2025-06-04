package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.service.UtenteService;

@Controller                                    // 1) Aggiunta dell’annotazione @Controller
public class testController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/test")
    public String test() {
        // 2) Restituisce un redirect verso l’endpoint "/artisti"
        return "test";
    }

}
