package it.uniroma3.siw.football.exception;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(Long id) {
        super("Team not found: " + id);
    }

}
