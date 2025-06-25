package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Artista> getArtistaById(Long id) {
        return artistaRepository.findById(id);
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

    public Artista modificaArtista(Artista artistaDalForm, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }

        Artista artistaOriginale = artistaRepository.findById(artistaDalForm.getId())
            .orElseThrow(() -> new RuntimeException("Artista non trovato"));

        // Aggiorna tutti i campi modificabili
        artistaOriginale.setNome(artistaDalForm.getNome());
        artistaOriginale.setAnnoNascita(artistaDalForm.getAnnoNascita());
        artistaOriginale.setAnnoMorte(artistaDalForm.getAnnoMorte());
        artistaOriginale.setBiografia(artistaDalForm.getBiografia());
        artistaOriginale.setUrlImage(artistaDalForm.getUrlImage());
        artistaOriginale.setMuseo(artistaDalForm.getMuseo());

        return artistaRepository.save(artistaOriginale);
    }

    public Artista aggiornaArtista(Artista artistaDalForm, Utente utenteCheRichiede) {
    // Riusa il metodo modificaArtista per evitare duplicazioni
    return modificaArtista(artistaDalForm, utenteCheRichiede);
}


}