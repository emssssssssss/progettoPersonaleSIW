package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String login(@RequestParam(name = "redirect", required = false) String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login"; // il template login.html
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("errore", "Accesso fallito");
        return "login";
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
