package it.uniroma3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Prenotazione;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteService utenteService;

    public Prenotazione getPrenotazione(Long id) {
        return this.prenotazioneRepository.findById(id).get();
    }

    public Iterable<Prenotazione> getAllPrenotazioni() {
        return this.prenotazioneRepository.findAll();
    }
    
     public void cancellaPrenotazione(Long id, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        if(utenteCheRichiede.getId() != prenotazioneRepository.findById(id).get().getUtente().getId()) {
             throw new RuntimeException("Accesso negato: non è la tua prenotazione");
        }
        prenotazioneRepository.deleteById(id);
    }

     public Prenotazione aggiungiPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione modificaPrenotazione(Prenotazione prenotazione, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
         if(utenteCheRichiede.getId() != prenotazione.getUtente().getId()) {
             throw new RuntimeException("Accesso negato: non è la tua prenotazione");
        }
        return prenotazioneRepository.save(prenotazione);
    }

}
