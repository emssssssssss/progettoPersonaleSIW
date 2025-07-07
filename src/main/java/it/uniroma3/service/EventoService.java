package it.uniroma3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Evento;
import it.uniroma3.model.Opera;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private OperaService operaService;

    public Evento getEventoById(Long id) {
        return this.eventoRepository.findByIdWithOpereAndArtisti(id);
    }

    public Iterable<Evento> getAllEventi() {
        return this.eventoRepository.findAll();
    }

    public Evento getEventoByTitolo(String titolo) {
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

    /*
     * @Transactional
     * public void rimuoviOperaDaEvento(Long eventoId, Long operaId) {
     * Evento evento = eventoRepository.findByIdWithOpereAndArtisti(eventoId);
     * Opera opera = operaService.getOperaById(operaId).get();
     * 
     * List<Opera> opere = evento.getOpere();
     * opere.remove(opera);
     * evento.setOpere(opere);
     * eventoRepository.save(evento);
     * 
     * 
     * List<Evento> eventi = opera.getEventi();
     * eventi.remove(evento);
     * opera.setEventi(eventi);
     * operaService.aggiungiOpera(opera);
     * }
     */

    @Transactional
    public void rimuoviOperaDaEvento(Long eventoId, Long operaId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Evento non trovato: " + eventoId));

        Opera opera = operaService.getOperaById(operaId)
                .orElseThrow(() -> new IllegalArgumentException("Opera non trovata: " + operaId));

        if (evento.getOpere() == null) {
            throw new IllegalStateException("La lista opere dell'evento è null.");
        }

        boolean removed = evento.getOpere().remove(opera);
        System.out.println(">>> Opera rimossa da Evento? " + removed);

        eventoRepository.save(evento);
    }

}
