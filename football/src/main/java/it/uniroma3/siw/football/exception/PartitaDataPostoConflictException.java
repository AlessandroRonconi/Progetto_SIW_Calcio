package it.uniroma3.siw.football.exception;

import java.time.LocalDateTime;

public class PartitaDataPostoConflictException extends RuntimeException {
    public PartitaDataPostoConflictException(LocalDateTime dataOra, String posto) {
        super(posto + "in data e ora " + dataOra + " è già occupato da un'altra partita");
    }

}
