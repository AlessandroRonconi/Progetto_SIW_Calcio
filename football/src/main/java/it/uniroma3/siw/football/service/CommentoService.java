package it.uniroma3.siw.football.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Commento;
import it.uniroma3.siw.football.model.Credentials;
import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.model.User;
import it.uniroma3.siw.football.repository.CommentoRepository;
import it.uniroma3.siw.football.repository.CredentialsRepository;
import it.uniroma3.siw.football.repository.PartitaRepository;

@Service
public class CommentoService {

    private final CommentoRepository commentoRepository;
    private final PartitaRepository gameRepository;
    private final CredentialsRepository credentialsRepository;

    public CommentoService(CommentoRepository commentoRepository, PartitaRepository gameRepository,
            CredentialsRepository credentialsRepository) {
        this.commentoRepository = commentoRepository;
        this.gameRepository = gameRepository;
        this.credentialsRepository = credentialsRepository;
    }

    @Transactional
    public Commento createCommento(Long id, String username, String text) {
        Partita game = this.gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partita non trovata."));
        Credentials credentials = this.credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Credenziali non trovate."));
        User user = credentials.getUser();
        Commento commento = new Commento();
        commento.setAuthor(user);
        commento.setText(text);
        commento.setPartita(game);
        commento.setDateTime(LocalDateTime.now());
        return commentoRepository.save(commento);
    }

    @Transactional
    public Commento save(Commento c) {
        return this.commentoRepository.save(c);
    }

    @Transactional
    public Commento update(Commento c) {
        return this.commentoRepository.save(c);
    }

    @Transactional(readOnly = true)
    public Commento findById(Long id) {
        return this.commentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commento non trovato."));
    }

    @Transactional(readOnly = true)
    public boolean isNotOwner(Commento commento, String username) {
        return !(commento.getAuthor().getUsername().equals(username));
    }

    @Transactional(readOnly = true)
    public void checkOwner(Commento commento, String username) {
        if (!commento.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Non autorizzato");
        }
    }
}
