package it.uniroma3.siw.football.exception;

public class TournamentNotFoundException extends Exception {

    public TournamentNotFoundException(Long tournamentId) {
        super("Tournament not found: " + tournamentId);
    }

}