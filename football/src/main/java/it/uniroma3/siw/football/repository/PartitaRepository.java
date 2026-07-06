package it.uniroma3.siw.football.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.model.Torneo;

public interface PartitaRepository extends CrudRepository<Partita, Long> {
    @Query("SELECT p FROM Partita p LEFT JOIN FETCH p.homeTeam LEFT JOIN FETCH p.awayTeam LEFT JOIN FETCH p.arbitro JOIN FETCH p.torneo WHERE p.torneo.id = :id ORDER BY p.dateTime ASC")
    List<Partita> findByTournamentFetchTeamsAndReferee(@Param("id") Long id);

    List<Partita> findByTorneoIdOrderByDateTimeAsc(Long torneoId); //solo per i test sulle strategie di fetch

    @Query("SELECT p FROM Partita p LEFT JOIN FETCH p.homeTeam LEFT JOIN FETCH p.awayTeam WHERE p.status = 'PLAYED' AND (p.homeTeam.id = :teamId OR p.awayTeam.id = :teamId)")
    List<Partita> findPlayedByTeam(@Param("teamId") Long teamId);

    @Query("SELECT p FROM Partita p LEFT JOIN FETCH p.homeTeam LEFT JOIN FETCH p.awayTeam WHERE (p.homeTeam.id = :teamId OR p.awayTeam.id = :teamId)")
    List<Partita> findByTeam(@Param("teamId") Long teamId);

    boolean existsByHomeTeamAndAwayTeamAndTorneoAndIdNot(Squadra homeTeam, Squadra awayTeam, Torneo torneo, Long id);

    @Query("SELECT p FROM Partita p WHERE p.dateTime = :dateTime AND p.place = :place AND p.id <> :excludeId")
    List<Partita> findByDateTimeAndPlaceExcluding(@Param("dateTime") LocalDateTime dateTime,
            @Param("place") String place, @Param("excludeId") Long excludeId);
}
