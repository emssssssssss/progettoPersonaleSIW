package it.uniroma3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Artista;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.ArtistaRepository;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private UtenteService utenteService;

    private Artista getArtistaById(Long id) {
        return artistaRepository.findById(id).orElse(null);
    }

    private Iterable<Artista> getAllArtisti() {
        return artistaRepository.findAll();
    }

    private List<Artista> getArtistaPerNome(String nome) {
        return this.artistaRepository.findByNomeContaining(nome);
    }

     public void cancellaArtista(Long id, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        artistaRepository.deleteById(id);
    }

     public Artista aggiungiArtista(Artista artista, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return artistaRepository.save(artista);
    }

    public Artista modificaArtista(Artista artista, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return artistaRepository.save(artista);
    }
}