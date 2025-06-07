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
    Prenotazione prenotazione = prenotazioneRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));

    // Se non admin e non proprietario -> Accesso negato
    if (!utenteService.isAdmin(utenteCheRichiede) && 
        !utenteCheRichiede.getId().equals(prenotazione.getUtente().getId())) {
        throw new RuntimeException("Accesso negato: non puoi cancellare questa prenotazione");
    }
    prenotazioneRepository.deleteById(id);
}

    public Prenotazione modificaPrenotazione(Prenotazione prenotazione, Utente utenteCheRichiede) {
        Prenotazione existing = prenotazioneRepository.findById(prenotazione.getId())
            .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));

        if (!utenteService.isAdmin(utenteCheRichiede) &&
            !utenteCheRichiede.getId().equals(existing.getUtente().getId())) {
            throw new RuntimeException("Accesso negato: non puoi modificare questa prenotazione");
        }
        // puoi aggiungere eventuali controlli di validità sui dati della prenotazione

        return prenotazioneRepository.save(prenotazione);
    }


     public Prenotazione aggiungiPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

   

}
