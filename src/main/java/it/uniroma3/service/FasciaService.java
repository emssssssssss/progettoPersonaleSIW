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
        return this.fasciaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Fascia non trovata"));
    }

    public Iterable<Fascia> getAllFascia() {
        return this.fasciaRepository.findAll();
    }

    public void cancellaFascia(Long id, Utente utente) {
        verificaAccessoStaff(utente);
        if (!fasciaRepository.existsById(id)) {
            throw new RuntimeException("Fascia con ID" + id + "non trovata");
        }
        fasciaRepository.deleteById(id);
    }

    private void verificaAccessoStaff(Utente utente){
        if(!utenteService.isAdmin(utente)){
            throw new RuntimeException("Accesso negato");
        }
    }


    public Fascia salvaFascia(Fascia fascia, Utente utente){
        verificaAccessoStaff(utente);
        return fasciaRepository.save(fascia);
    }

}
