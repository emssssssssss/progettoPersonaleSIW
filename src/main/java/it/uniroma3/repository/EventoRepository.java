package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.uniroma3.model.Evento;



public interface EventoRepository extends JpaRepository<Evento, Long> {
    Evento findByTitolo(String titolo);

    @Query("SELECT e FROM Evento e LEFT JOIN FETCH e.fasceOrarie")
    List<Evento> findAllWithFasceOrarie();
}
