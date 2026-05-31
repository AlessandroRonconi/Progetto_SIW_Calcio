package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.TournamentNotFoundException;
import it.uniroma3.siw.football.model.ClassificationRow;
import it.uniroma3.siw.football.model.Team;
import it.uniroma3.siw.football.model.Tournament;
import it.uniroma3.siw.football.repository.GameRepository;
import it.uniroma3.siw.football.repository.TournamentRepository;

@Service
public class TournamentService {
    private GameRepository gameRepository;
    private TournamentRepository tournamentRepository;
    
    public TournamentService(TournamentRepository tournamentRepository, GameRepository gameRepository) {
        this.tournamentRepository = tournamentRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<Tournament> findAll() {
        List<Tournament> list = new ArrayList<>();
        this.tournamentRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public Tournament save(Tournament tournament) {
        return this.tournamentRepository.save(tournament);
    }

    @Transactional(readOnly = true)
    public Tournament findById(Long id) {
        return this.tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }

    @Transactional
    public Long count() {
        return this.tournamentRepository.count();
    }

    @Transactional(readOnly = true)
    public List<ClassificationRow> getClassificationTable(Long tournamentId) {
        Tournament tournament = this.findById(tournamentId);
        if (tournament == null)
            return new ArrayList<>();

        // Prende i team partecipanti
        List<Team> teams = tournament.getTeams();

        List<ClassificationRow> rows = new ArrayList<>();

        for (Team team : teams) {
            // Filtra solo i match giocati da questa squadra
            rows.add(new ClassificationRow(team, gameRepository.findPlayedByTeam(team.getId())));
        }

        // Ordina per Punti (e Differenza Reti come secondo criterio)
        rows.sort((r1, r2) -> {
            int res = Integer.compare(r2.getPoints(), r1.getPoints());
            if (res == 0) {
                return Integer.compare(r2.getGoalsDifference(), r1.getGoalsDifference());
            }
            return res;
        });

        return rows;
    }

}
