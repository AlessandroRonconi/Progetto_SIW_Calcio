package it.uniroma3.siw.football.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.model.Game;
import it.uniroma3.siw.football.repository.GameRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game save(Game game) {
        return this.gameRepository.save(game);
    }
}
