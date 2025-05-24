package it.uniroma3.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Evento;



public interface EventoRepository extends CrudRepository<Evento, Long>{
    Evento findByTitolo(String titolo);
}
