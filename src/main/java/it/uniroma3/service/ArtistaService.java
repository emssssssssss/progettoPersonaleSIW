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
    public UtenteService utenteService;  //per controllare i ruoli

    public Artista getArtistaById(Long id) {
        return artistaRepository.findById(id).orElse(null);
    }

    public Iterable<Artista> getAllArtisti() {
        return artistaRepository.findAll();
    }

    public List<Artista> getArtistaPerNome(String nome) {
        return this.artistaRepository.findByNomeContaining(nome);
    }



    //cancellazione con controllo admin
    public void cancellaArtista(Long id, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        artistaRepository.deleteById(id);
    }

    //aggiunta con controllo admin
    public Artista aggiungiArtista(Artista artista, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return artistaRepository.save(artista);
    }

    //modifica con controllo admin
    public Artista modificaArtista(Artista artista, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return artistaRepository.save(artista);
    }
}