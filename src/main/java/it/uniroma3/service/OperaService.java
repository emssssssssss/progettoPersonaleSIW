package it.uniroma3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Opera;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.OperaRepository;

@Service
public class OperaService {

    @Autowired
    private OperaRepository operaRepository;

    @Autowired
    private UtenteService utenteService;

    public Optional<Opera> getOperaById(Long id) {
        return operaRepository.findById(id);
    }

    public Iterable<Opera> getAllOpere() {
        return this.operaRepository.findAll();
    }

    public Opera getOperaByTitolo(String titolo) {
        return this.operaRepository.findByTitolo(titolo);
    }

    public void cancellaOpera(Long id, Optional<Utente> utenteOpt) {
        Utente utente = utenteOpt.orElseThrow(() -> new RuntimeException("Utente non trovato"));
        if (!utenteService.isAdmin(utente)) {
            throw new RuntimeException("Accesso negato: non sei staff");
        }
        operaRepository.deleteById(id);
    }

    public Opera aggiungiOpera(Opera opera) {
        return operaRepository.save(opera);
    }

    public Opera modificaOpera(Opera operaDalForm, Optional<Utente> utenteOpt) {
        Utente utente = utenteOpt.orElseThrow(() -> new RuntimeException("Utente non trovato"));
        if (!utenteService.isAdmin(utente)) {
            throw new RuntimeException("Accesso negato: non sei staff");
        }

        Opera operaOriginale = operaRepository.findById(operaDalForm.getId())
                .orElseThrow(() -> new RuntimeException("Opera non trovata"));

        // aggiorna solo i campi modificati
        operaOriginale.setTitolo(operaDalForm.getTitolo());
        operaOriginale.setDescrizione(operaDalForm.getDescrizione());
        operaOriginale.setAnno(operaDalForm.getAnno());
        operaOriginale.setArtista(operaDalForm.getArtista());
        operaOriginale.setMuseo(operaDalForm.getMuseo());

        return operaRepository.save(operaOriginale);
    }
}
