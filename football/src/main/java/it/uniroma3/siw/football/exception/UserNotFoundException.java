package it.uniroma3.siw.football.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User not found exception: " + username);
    }
}
