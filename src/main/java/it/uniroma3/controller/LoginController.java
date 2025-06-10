package it.uniroma3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostraLogin(@RequestParam(required = false) String error,
                               Model model) {
        if (error != null) {
            model.addAttribute("errore", "Username o password non validi");
        }
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("errore", "Accesso fallito");
        return "login";
    }
}
