package it.uniroma3.siw.football.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found exception: " + id);
    }
}
