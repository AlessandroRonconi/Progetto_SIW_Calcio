package it.uniroma3.siw.football.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Squadra;

public interface SquadraRepository extends CrudRepository<Squadra, Long> {

    Optional<Squadra> findByName(String name);

}
