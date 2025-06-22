package it.uniroma3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import it.uniroma3.model.Utente;
import it.uniroma3.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }

    public Iterable<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> autenticaUtente(String identificatore, String rawPassword) {
        Optional<Utente> optionalUtente = utenteRepository.findByEmail(identificatore);

        if (optionalUtente.isEmpty()) {
            optionalUtente = utenteRepository.findByNome(identificatore);
        }

        return optionalUtente.filter(utente -> passwordEncoder.matches(rawPassword, utente.getPassword()));
    }

    public Utente getUtenteByNomeEPassword(String nome, String password) {
        Optional<Utente> optUtente = utenteRepository.findByNome(nome);
        if (optUtente.isPresent()) {
            Utente utente = optUtente.get();
            if (passwordEncoder.matches(password, utente.getPassword())) {
                return utente;
            }
        }
        return null;
    }

    public Optional<Utente> getUtenteByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Utente getUtenteByEmailEPassword(String email, String password) {
        Optional<Utente> optUtente = utenteRepository.findByEmail(email);
        if (optUtente.isPresent()) {
            Utente utente = optUtente.get();
            if (passwordEncoder.matches(password, utente.getPassword())) {
                return utente;
            }
        }
        return null;
    }

    public Optional<Utente> getUtenteByName(String name) {
        return utenteRepository.findByNome(name);
    }

    public boolean isAdmin(Utente utente) {
        return utente != null && utente.getRuolo() == Utente.Ruolo.STAFF;
    }

    public boolean isAmministartore (String codice) {
        return codice.equals("Panino_con_pomodori");
    }

    public boolean permessoAdmin(String codice) {
        return "HanShotFirst".equals(codice);
    }

    // Cancella un utente (solo admin)
    public void cancellaUtente(Long id, Utente utenteCheRichiede) {
        if (!isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        utenteRepository.deleteById(id);
    }

    public void addUtente(Utente utente) {
        // cifra la password prima di salvarla
        String hashedPassword = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(hashedPassword);
        this.utenteRepository.save(utente);

    }

    public void modificaUtente(Utente utente) {
        this.utenteRepository.save(utente);
    }

    public Utente getUtenteAutenticato() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email;
        if (principal instanceof UserDetails details) {
            email = details.getUsername();
        } else {
            email = principal.toString();
        }

        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente autenticato non trovato: " + email));
    }

}
