package it.uniroma3.controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

//import it.uniroma3.model.Utente;
import it.uniroma3.service.UtenteService;
//import jakarta.validation.Valid;


@Controller
public class UtenteController {

    @Autowired 
    private UtenteService utenteService;

    @GetMapping("/utente/{id}")
    public String getUtente(@PathVariable Long id, Model model) {
        model.addAttribute("utente", this.utenteService.getUtenteById(id));
        return "utente";
    }

    @GetMapping("/utenti")
    public String getUtenti(Model model) {
        model.addAttribute("utenti", this.utenteService.getAllUtenti());
        return "utenti";
    }

    /* 
    @GetMapping("/login")
    public String inizia() {
        return "login";
    }

     @PostMapping("/login")
    public String login(@RequestParam String nomeUtente, @RequestParam String password, Model model) {
        
        Optional<Utente> optionalUtente = utenteService.autenticaUtente(nomeUtente, password);
        if(optionalUtente.isEmpty()){
            model.addAttribute("errore", "credenziali non valide");
            return "login";
        }

        Utente utente = optionalUtente.get();
        
        
        return "redirect:/utente/" + utente.getId();
    } */
/* 
    // mostra la pagina di registrazione e aggiunge un oggetto Utente vuoto per il binding del form
    @GetMapping("/registrazione")
    public String mostraRegistrazione(Model model) {
        model.addAttribute("utente", new Utente());
        return "registrazione";
    }

    // gestisce l'invio del form di registrazione con validazione automatica e controllo passwordBis
    @PostMapping("/registrazione")
    public String registrazione(//@RequestParam String nomeUtente,
                                //@RequestParam String email,
                                //@RequestParam String password,
                                //@RequestParam String codiceAmministratore,
                                //@RequestParam String passwordBis,
                                //Model model
                                
                                @ModelAttribute("utente") @Valid Utente utente,        // Lega il form all'oggetto e attiva la validazione
                                BindingResult bindingResult,                           // Contiene eventuali errori di validazione
                                @RequestParam String passwordBis,                      // Campo di conferma password 
                                @RequestParam(required = false) String codiceAmministratore,
                                Model model) {
      

        // Controllo manuale della conferma password
        if (!utente.getPassword().equals(passwordBis)) {
            model.addAttribute("errorePassword", "La conferma password non combacia con la password inserita");
        }

        // Controllo unicità email 
        if (this.utenteService.getUtenteByEmail(utente.getEmail()) != null) {
            bindingResult.rejectValue("email", "duplicate", "Email già in uso");
        }

        // Se ci sono errori di validazione o errore passwordBis, ritorna al form con errori mostrati
        if (bindingResult.hasErrors() || model.containsAttribute("errorePassword")) {
            return "registrazione"; 
        }

        // Imposta ruolo in base al codice amministratore
        if (this.utenteService.permessoAdmin(codiceAmministratore) == false) {
            utente.setRuolo(Utente.Ruolo.VISITATORE);
        } else {
            utente.setRuolo(Utente.Ruolo.STAFF);
        }

        this.utenteService.addUtente(utente);

        // Reindirizza alla pagina profilo dell'utente appena registrato
        return "redirect:/utente/" + utente.getId();
    }
    
    */
}
