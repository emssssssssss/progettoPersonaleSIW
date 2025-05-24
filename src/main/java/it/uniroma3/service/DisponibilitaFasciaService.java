package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.DisponibilitaFascia;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.DisponibilitaFasciaRepository;

@Service
public class DisponibilitaFasciaService {

    @Autowired
    private DisponibilitaFasciaRepository disponibilitaFasciaRepository;

    @Autowired
    private UtenteService utenteService;

    public DisponibilitaFascia getDisponibilitaFasciaById(Long id) {
        return this.disponibilitaFasciaRepository.findById(id).get();
    }

    public Iterable<DisponibilitaFascia> getAllDisponibilitaFascia() {
        return this.disponibilitaFasciaRepository.findAll();
    }

    public void cancellaDisponibilitaFascia(Long id, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        disponibilitaFasciaRepository.deleteById(id);
    }

     public DisponibilitaFascia aggiungiDisponibilitaFascia(DisponibilitaFascia disponibilita, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return disponibilitaFasciaRepository.save(disponibilita);
    }

    public DisponibilitaFascia modificaDisponibilitaFascia(DisponibilitaFascia disponibilita, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return disponibilitaFasciaRepository.save(disponibilita);
    }

}
