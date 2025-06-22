package it.uniroma3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.model.Utente;
import it.uniroma3.service.CustomUserDetailsService;
import it.uniroma3.service.MuseoService;
import it.uniroma3.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private MuseoService museoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public String mostraLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errore", "Email o password non validi");
        }
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("errore", "Accesso fallito");
        return "login";
    }

    @GetMapping("/default")
    public String mostraHomepage(Model model, Authentication authentication) {
        model.addAttribute("museo", museoService.getMuseoUnico());

        String nome = authentication.getName();

        // Prova prima con il nome, poi con la mail se non trovato
        Optional<Utente> optionalUtente = utenteService.getUtenteByName(nome);
        if (optionalUtente.isEmpty()) {
            optionalUtente = utenteService.getUtenteByEmail(nome);
        }

        Utente utente = optionalUtente
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con nome o email: " + nome));

        model.addAttribute("utente", utente);
        return "homepage";
    }

    @GetMapping("/registrazione")
    public String mostraRegistrazione() {
        return "Registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazione(@RequestParam String email,
            @RequestParam String nome,
            @RequestParam String password,
            @RequestParam String codiceAmministratore,
            @RequestParam String passwordBis,
            Model model,
            HttpServletRequest request) {

        if (!password.equals(passwordBis)) {
            model.addAttribute("errore", "La conferma Password non combacia con la Password inserita");
            return "Registrazione";
        }

        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setNome(nome);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        utente.setPassword(hashedPassword);
        if (this.utenteService.isAmministartore(codiceAmministratore)) {
            utente.setRuolo(Utente.Ruolo.STAFF);
        } else {
            utente.setRuolo(Utente.Ruolo.VISITATORE);
        }

        this.utenteService.addUtente(utente);

        // Auto-login qui:
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(utente.getEmail());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        // 🔑 Salva il contesto nella sessione
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return "redirect:/homepage";
    }

}
