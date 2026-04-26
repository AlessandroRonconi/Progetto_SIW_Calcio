package it.uniroma3.siw.football.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.GameNotFoundException;
import it.uniroma3.siw.football.model.Game;
import it.uniroma3.siw.football.model.enums.GameStatus;
import it.uniroma3.siw.football.repository.GameRepository;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game save(Game game) {
        return this.gameRepository.save(game);
    }

    @Transactional(readOnly = true)
    public List<Game> gamesByTournament(Long tId) {
        return gameRepository.findByTournamentFetchTeams(tId);
    }

    @Transactional
    public void updateResult(Long id, int home, int away) {
        Game p = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        p.setGoalsHome(home);
        p.setGoalsAway(away);
        p.setStatus(GameStatus.PLAYED);
    }
}
