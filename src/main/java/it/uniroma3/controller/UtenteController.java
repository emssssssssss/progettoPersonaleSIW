package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.model.Utente;
import it.uniroma3.service.UtenteService;


@Controller
public class UtenteController {

    @Autowired UtenteService utenteService;

    @GetMapping("/utente/{id}")
    public String getUtente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("utente", this.utenteService.getUtenteById(id));
        return "Utente";
    }

    @GetMapping("/utenti")
    public String getUtenti(Model model) {
        model.addAttribute("utenti", this.utenteService.getAllUtenti());
        return "Utenti";
    }

     @GetMapping("/accedi")
    public String inizia() {
        return "Accedi";
    }

     @PostMapping("/accedi")
    public String login(@RequestParam String nomeUtente, @RequestParam String password, Model model) {
        Utente utente = this.utenteService.getUtenteByNomeUtenteEPassword(nomeUtente, password);
        if(utente == null){
            utente= this.utenteService.getUtenteByEmailEPassword(nomeUtente, password);
            if(utente == null){
                model.addAttribute("errore", "Nessun account trovato con queste credenziali");
                return "Accedi";
            }
            else{
                return "redirect:/utente/" + utente.getId();
            }
        }
        else{
            return "redirect:/utente/" + utente.getId();
        }
    }

     @GetMapping("/registrazione")
    public String mostraRegistrazione() {
        return "Registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazione(@RequestParam String nomeUtente,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String codiceAmministratore,
                                @RequestParam String passwordBis,
                                Model model) {
        if(!password.equals(passwordBis)) {
             model.addAttribute("errore", "La conferma Password non combacia con la Password inserita");
            return "Registrazione";
        }

        Utente utente = new Utente();
        utente.setNome(nomeUtente);
        utente.setEmail(email);
        utente.setPassword(password);
        if(this.utenteService.permessoAdmin(codiceAmministratore)==false) {
            utente.setRuolo(Utente.Ruolo.VISITATORE);}
        else {
            utente.setRuolo(Utente.Ruolo.STAFF);
        }
        this.utenteService.addUtente(utente);
        return "redirect:/utente/" + utente.getId();
    }
    
    
}
