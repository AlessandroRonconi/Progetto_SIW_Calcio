package it.uniroma3.siw.football.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Commento;
import it.uniroma3.siw.football.model.Partita;

public interface CommentoRepository extends CrudRepository<Commento, Long> {
    List<Commento> findByPartita(Partita partita);
}
