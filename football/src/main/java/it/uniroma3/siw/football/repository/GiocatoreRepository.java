package it.uniroma3.siw.football.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.Giocatore;

public interface GiocatoreRepository extends CrudRepository<Giocatore, Long> {
    List<Giocatore> findBySquadraIsNull();

    Optional<Giocatore> findByFirstNameAndLastNameAndDateOfBirth(
            String firstName, String lastName, LocalDate dateOfBirth);
}