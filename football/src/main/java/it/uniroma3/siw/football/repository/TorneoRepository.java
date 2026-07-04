package it.uniroma3.siw.football.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Torneo;

public interface TorneoRepository extends CrudRepository<Torneo, Long> {
    Optional<Torneo> findByNameAndYear(String name, Long year);
}