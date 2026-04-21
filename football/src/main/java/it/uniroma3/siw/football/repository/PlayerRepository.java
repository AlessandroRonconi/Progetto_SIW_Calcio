package it.uniroma3.siw.football.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>{

}
