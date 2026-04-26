package it.uniroma3.siw.football.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.model.Comment;
import it.uniroma3.siw.football.repository.CommentRepository;

public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }

    @Transactional
    public Comment save(Comment c){
        return this.commentRepository.save(c);
    }

    @Transactional(readOnly=true)
    public List<Comment> findByGame(Long gId){
        return this.commentRepository.findByGameId(gId);
    }

    @Transactional
    public Comment update(Comment c){
        return this.commentRepository.save(c);
    }
}
