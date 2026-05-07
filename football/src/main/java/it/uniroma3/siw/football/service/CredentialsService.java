package it.uniroma3.siw.football.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.football.model.Credentials;
import it.uniroma3.siw.football.repository.CredentialsRepository;

@Service
public class CredentialsService {
    private CredentialsRepository credentialsRepository;
    private PasswordEncoder passwordEncoder;

    public CredentialsService(CredentialsRepository credentialsRepository,
            PasswordEncoder passwordEncoder) {
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Credentials getCredentials(Long id) {
        return this.credentialsRepository.findById(id).get();
    }

    public Credentials getCredentials(String username) {
        return this.credentialsRepository.findByUsername(username).get();
    }

    public Credentials saveCredentials(Credentials credentials) {
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
}
