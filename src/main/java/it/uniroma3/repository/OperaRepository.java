package it.uniroma3.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Opera;


public interface OperaRepository extends CrudRepository<Opera, Long>{
    Opera findByTitolo(String titolo);
    Optional<Opera> findById(Long id);
}
