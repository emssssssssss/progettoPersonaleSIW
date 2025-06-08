package it.uniroma3.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Museo;
import it.uniroma3.repository.MuseoRepository;

@Service
public class MuseoService {

    @Autowired
    private MuseoRepository museoRepository;

    private final static Long ID_MUSEO_UNICO = 1L;

    public Museo getMuseoUnico() {
        return museoRepository.findById(ID_MUSEO_UNICO)
            .orElseThrow(() -> new NoSuchElementException("Museo non trovato con id: 1"));
    }

    
    //modifica i dati del museo
    public Museo aggiornaMuseo(Museo museoModificato){
        museoModificato.setId(ID_MUSEO_UNICO);
        return museoRepository.save(museoModificato);
    }
}
