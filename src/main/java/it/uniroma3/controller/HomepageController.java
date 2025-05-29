package it.uniroma3.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomepageController {
    
    @GetMapping("/homepage")
    public String mostraHomepage(){
        return "homepage";
    }
}
