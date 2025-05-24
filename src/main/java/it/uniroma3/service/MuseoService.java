package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Museo;
import it.uniroma3.repository.MuseoRepository;

@Service
public class MuseoService {

    @Autowired
    private MuseoRepository museoRepository;

    public Museo getMuseo(Long id) {
        return this.museoRepository.findById(id).get();
    }

    public Iterable<Museo> getAllMusei() {
        return this.museoRepository.findAll();
    }
}
