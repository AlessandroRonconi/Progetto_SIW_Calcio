package it.uniroma3.siw.football.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("Game not found exception: " + id);
    }
}
