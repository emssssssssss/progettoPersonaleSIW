package it.uniroma3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Evento;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    public Evento getEventoById(Long id) {
        return this.eventoRepository.findById(id).get();
    }

    public Iterable<Evento> getAllEventi() {
        return this.eventoRepository.findAll();
    }

    public Evento getEventoByTitolo (String titolo) {
        return this.eventoRepository.findByTitolo(titolo);
    }

    public void cancellaEvento(Long id, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        eventoRepository.deleteById(id);
    }

     public Evento aggiungiEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento modificaEvento(Evento evento, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return eventoRepository.save(evento);
    }

    public List<Evento> getAllEventiConFasce() {
    return eventoRepository.findAllWithFasceOrarie();
}

}