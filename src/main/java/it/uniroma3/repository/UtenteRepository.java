package it.uniroma3.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Utente;


public interface UtenteRepository extends CrudRepository<Utente, Long>{
    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByNome(String nome);
}
