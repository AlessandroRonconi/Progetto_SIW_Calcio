package it.uniroma3.siw.football.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Game;

public interface GameRepository extends CrudRepository<Game, Long>{

}
