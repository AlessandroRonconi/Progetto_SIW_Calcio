package it.uniroma3.siw.football.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {

}
