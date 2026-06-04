package it.uniroma3.siw.football.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Commento;

public interface CommentoRepository extends CrudRepository<Commento, Long> {
    List<Commento> findByGameId(Long gameId);
}
