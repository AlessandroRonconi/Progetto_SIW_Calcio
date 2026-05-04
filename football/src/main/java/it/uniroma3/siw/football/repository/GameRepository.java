package it.uniroma3.siw.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
    @Query("SELECT p FROM Game p JOIN FETCH p.homeTeam JOIN FETCH p.awayTeam WHERE p.torneo.id = :id ORDER BY p.dateTime ASC")
    List<Game> findByTournamentFetchTeams(Long id);
}
