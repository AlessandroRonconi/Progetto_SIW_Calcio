package it.uniroma3.siw.football.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Giocatore;

public interface GiocatoreRepository extends CrudRepository<Giocatore, Long> {

    List<Giocatore> findBySquadraIsNull();
}
