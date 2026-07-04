package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.DuplicateGiocatoreException;
import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Giocatore;
import it.uniroma3.siw.football.repository.GiocatoreRepository;

@Service
public class GiocatoreService {

    private final GiocatoreRepository giocatoreRepository;

    public GiocatoreService(GiocatoreRepository giocatoreRepository) {
        this.giocatoreRepository = giocatoreRepository;
    }

    public Giocatore findById(Long id) {
        return this.giocatoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Giocatore non trovato."));
    }

    public List<Giocatore> findBySquadraIsNull() {
        return this.giocatoreRepository.findBySquadraIsNull();
    }

    @Transactional(readOnly = true)
    public List<Giocatore> findAll() {
        List<Giocatore> list = new ArrayList<>();
        this.giocatoreRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public Giocatore save(Giocatore giocatore) {
        Optional<Giocatore> existing = this.giocatoreRepository
                .findByFirstNameAndLastNameAndDateOfBirth(
                        giocatore.getFirstName(),
                        giocatore.getLastName(),
                        giocatore.getDateOfBirth());

        // Duplicato solo se esiste un ALTRO giocatore con gli stessi dati
        // (non se è lo stesso che sto modificando)
        if (existing.isPresent() && !existing.get().getId().equals(giocatore.getId())) {
            throw new DuplicateGiocatoreException(
                    "Esiste già un giocatore con nome, cognome e data di nascita identici.");
        }

        return this.giocatoreRepository.save(giocatore);
    }

    @Transactional
    public void delete(Long id) {
        if (!this.giocatoreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Giocatore non trovato.");
        }
        this.giocatoreRepository.deleteById(id);
    }
}