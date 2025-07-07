package it.uniroma3.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.model.Opera;


public interface OperaRepository extends JpaRepository<Opera, Long>{
    Opera findByTitolo(String titolo);
    Optional<Opera> findById(Long id);
}
