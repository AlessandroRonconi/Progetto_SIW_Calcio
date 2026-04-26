package it.uniroma3.siw.football.exception;

public class TournamentNotFoundException extends RuntimeException {

    public TournamentNotFoundException(Long id) {
        super("Tournament not found exception: "+id);
    }

}
