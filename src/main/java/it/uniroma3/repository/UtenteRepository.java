package it.uniroma3.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Utente;


public interface UtenteRepository extends CrudRepository<Utente, Long>{
    Utente findByNome(String nome);
    Utente findByNomeAndPassword(String nome, String password);
    Utente findByEmailAndPassword(String email, String password);
}
