package it.uniroma3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.model.Evento;



public interface EventoRepository extends JpaRepository<Evento, Long> {
    Evento findByTitolo(String titolo);
}
