package it.uniroma3.siw.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.football.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
    @Query("SELECT p FROM Game p JOIN FETCH p.homeTeam JOIN FETCH p.awayTeam WHERE p.tournament.id = :id ORDER BY p.dateTime ASC")
    List<Game> findByTournamentFetchTeams(Long id);

    @Query("SELECT g FROM Game g JOIN FETCH g.homeTeam JOIN FETCH g.awayTeam WHERE g.status = 'PLAYED' AND (g.homeTeam.id = :teamId OR g.awayTeam.id = :teamId)")
    List<Game> findPlayedByTeam(@Param("teamId") Long teamId);
}
