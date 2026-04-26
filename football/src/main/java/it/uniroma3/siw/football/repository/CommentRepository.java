package it.uniroma3.siw.football.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByGameId(Long gameId);
}
