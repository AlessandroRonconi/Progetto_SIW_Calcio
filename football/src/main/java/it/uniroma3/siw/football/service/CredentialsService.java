package it.uniroma3.siw.football.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Credentials;
import it.uniroma3.siw.football.repository.CredentialsRepository;

@Service
public class CredentialsService {
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;

    public CredentialsService(CredentialsRepository credentialsRepository,
            PasswordEncoder passwordEncoder) {
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Credentials getCredentials(Long id) {
        return this.credentialsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credenziali non trovate."));
    }

    public Credentials getCredentials(String username) {
        return this.credentialsRepository.findByUsername(username).get();
    }

    public Credentials saveCredentials(Credentials credentials) {
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
}
