package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Fascia;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.FasciaRepository;

@Service
public class FasciaService {

    @Autowired
    private FasciaRepository fasciaRepository;

    @Autowired
    private UtenteService utenteService;

    public Fascia getFasciaById(Long id) {
        return this.fasciaRepository.findById(id).get();
    }

    public Iterable<Fascia> getAllFascia() {
        return this.fasciaRepository.findAll();
    }

    public void cancellaFascia(Long id, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        fasciaRepository.deleteById(id);
    }

     public Fascia aggiungiFascia(Fascia disponibilita, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return fasciaRepository.save(disponibilita);
    }

    public Fascia modificaFascia(Fascia disponibilita, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return fasciaRepository.save(disponibilita);
    }

}
