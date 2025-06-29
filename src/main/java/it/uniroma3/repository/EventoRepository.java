package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Evento findByTitolo(String titolo);

    @Query("SELECT e FROM Evento e LEFT JOIN FETCH e.fasceOrarie")
    List<Evento> findAllWithFasceOrarie();

    @Query("select e from Evento e " +
            "left join fetch e.opere o " +
            "left join fetch o.artista a " +
            "left join fetch a.museo " +
            "where e.id = :id")
    Evento findByIdWithOpereAndArtisti(@Param("id") Long id);

}
