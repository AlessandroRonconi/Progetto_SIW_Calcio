package it.uniroma3.siw.football.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
