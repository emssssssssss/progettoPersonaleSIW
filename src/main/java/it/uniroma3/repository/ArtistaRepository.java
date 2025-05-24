package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Artista;



public interface ArtistaRepository extends CrudRepository<Artista, Long>{
     List<Artista> findByNomeContaining(String nome);
}
