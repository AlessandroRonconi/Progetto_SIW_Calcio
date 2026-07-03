package it.uniroma3.siw.football.exception;

public class DuplicateCredentialsException extends RuntimeException {
    
    public DuplicateCredentialsException(String message) {
        super(message);
    }
}