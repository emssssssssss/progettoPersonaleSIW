package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Utente;
import it.uniroma3.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente getUtenteById(Long id) {
        return utenteRepository.findById(id).get();
    }

    public Iterable<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Utente getUtenteByNomeUtenteEPassword(String nomeUtente, String password) {
        return this.utenteRepository.findByNomeUtenteAndPassword(nomeUtente, password);
    }

    public boolean isAdmin(Utente utente) {
        return utente != null && utente.getRuolo() == Utente.Ruolo.STAFF;
    }

    // Cancella un utente (solo admin)
    public void cancellaUtente(Long id, Utente utenteCheRichiede) {
        if (!isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        utenteRepository.deleteById(id);
    }

    public void addUtente (Utente utente) {
        this.utenteRepository.save(utente);
    }

    public void modificaUtente(Utente utente) {
        this.utenteRepository.save(utente);
    }
}
