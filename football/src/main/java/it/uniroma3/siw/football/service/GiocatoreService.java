package it.uniroma3.siw.football.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
