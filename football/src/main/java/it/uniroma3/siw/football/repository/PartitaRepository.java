package it.uniroma3.siw.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.football.model.Partita;

public interface PartitaRepository extends CrudRepository<Partita, Long> {
    @Query("SELECT p FROM Partita p JOIN FETCH p.homeTeam JOIN FETCH p.awayTeam WHERE p.torneo.id = :id ORDER BY p.dateTime ASC")
    List<Partita> findByTournamentFetchTeams(Long id);

    @Query("SELECT p FROM Partita p JOIN FETCH p.homeTeam JOIN FETCH p.awayTeam WHERE p.status = 'PLAYED' AND (p.homeTeam.id = :teamId OR p.awayTeam.id = :teamId)")
    List<Partita> findPlayedByTeam(@Param("teamId") Long teamId);
}
