package it.uniroma3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Prenotazione;
import it.uniroma3.model.Utente;
//import it.uniroma3.service.FasciaService;
import it.uniroma3.repository.FasciaRepository;
import it.uniroma3.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private FasciaRepository fasciaRepository;

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
        Long fasciaId = prenotazione.getFascia().getId();

        var fascia = fasciaRepository.findById(fasciaId)
            .orElseThrow(() -> new RuntimeException("Fascia non trovata"));

                if (fascia.getPostiPrenotati() >= fascia.getCapienzaMassima()){
                    throw new RuntimeException("Disponibilità esaurita: non ci sono posti prenotabili");
                }
            


        return prenotazioneRepository.save(prenotazione);
    }

   

}
