package it.uniroma3.siw.football.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Squadra;

public interface SquadraRepository extends CrudRepository<Squadra, Long> {

    boolean existsByName(String name);

}
