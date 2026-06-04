package it.uniroma3.siw.football.service;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Comment;
import it.uniroma3.siw.football.model.Credentials;
import it.uniroma3.siw.football.model.Game;
import it.uniroma3.siw.football.model.User;
import it.uniroma3.siw.football.repository.CommentRepository;
import it.uniroma3.siw.football.repository.CredentialsRepository;
import it.uniroma3.siw.football.repository.GameRepository;

public class CommentService {

    private CommentRepository commentRepository;
    private GameRepository gameRepository;
    private CredentialsRepository credentialsRepository;

    public CommentService(CommentRepository commentRepository, GameRepository gameRepository,
            CredentialsRepository credentialsRepository) {
        this.commentRepository = commentRepository;
        this.gameRepository = gameRepository;
        this.credentialsRepository = credentialsRepository;
    }

    @Transactional
    public Comment createComment(Long id, String username, String text) {
        Game game = this.gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partita non trovata."));
        Credentials credentials = this.credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Credenziali non trovate."));
        User user = credentials.getUser();
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setText(text);
        comment.setGame(game);
        comment.setDateTime(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment save(Comment c) {
        return this.commentRepository.save(c);
    }

    @Transactional
    public Comment update(Comment c) {
        return this.commentRepository.save(c);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return this.commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commento non trovato."));
    }

    @Transactional(readOnly = true)
    public boolean isNotOwner(Comment comment, String username) {
        return !(comment.getAuthor().getUsername().equals(username));
    }

    @Transactional(readOnly = true)
    public void checkOwner(Comment comment, String username) {
        if (!comment.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Non autorizzato");
        }
    }
}
