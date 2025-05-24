package it.uniroma3.service;

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

    public Opera getOperaById(Long id) {
        return this.operaRepository.findById(id).get();
    }

    public Iterable<Opera> getAllOpere() {
        return this.operaRepository.findAll();
    }

    public Opera getOperaByTitolo (String titolo) {
        return this.operaRepository.findByTitolo(titolo);
    }

     public void cancellaOpera(Long id, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        operaRepository.deleteById(id);
    }

     public Opera aggiungiOpera(Opera opera, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return operaRepository.save(opera);
    }

    public Opera modificaOpera(Opera opera, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return operaRepository.save(opera);
    }
}
