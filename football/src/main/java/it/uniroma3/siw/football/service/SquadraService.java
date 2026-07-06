package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.DuplicateSquadraException;
import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Giocatore;
import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.model.Torneo;
import it.uniroma3.siw.football.repository.PartitaRepository;
import it.uniroma3.siw.football.repository.SquadraRepository;

@Service
public class SquadraService {
    private final SquadraRepository squadraRepository;
    private final PartitaRepository partitaRepository;

    public SquadraService(SquadraRepository squadraRepository, PartitaRepository partitaRepository) {
        this.squadraRepository = squadraRepository;
        this.partitaRepository = partitaRepository;
    }

    @Transactional(readOnly = true)
    public List<Squadra> findAll() {
        List<Squadra> list = new ArrayList<>();
        this.squadraRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public void save(Squadra nuovaSquadra) {
        Optional<Squadra> existing = this.squadraRepository.findByName(nuovaSquadra.getName());
        if (existing.isPresent() && !existing.get().getId().equals(nuovaSquadra.getId())) {
            throw new DuplicateSquadraException("La squadra di nome '" + nuovaSquadra.getName() + "' esiste già");
        }
        this.squadraRepository.save(nuovaSquadra);
    }

    @Transactional
    public Squadra findById(Long id) {
        return this.squadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Squadra non trovata."));
    }

    @Transactional
    public void editSquadra(Long id, String nome, Long anno, String citta) {
        Squadra squadra = this.findById(id);
        squadra.setName(nome);
        squadra.setYearOfFoundation(anno);
        squadra.setCity(citta);
        this.save(squadra);
    }

    public Long count() {
        return this.squadraRepository.count();
    }

    @Transactional
    public void delete(Long id) {
        Squadra squadra = this.findById(id);

        if (squadra.getGiocatori() != null) {
            List<Giocatore> giocatori = new ArrayList<>(squadra.getGiocatori());
            for (Giocatore g : giocatori) {
                squadra.removeGiocatore(g);
            }
        }

        List<Partita> partite = partitaRepository.findByTeam(id);
        for (Partita p : partite) {
            if (squadra.equals(p.getHomeTeam())) {
                p.setHomeTeam(null);
            }
            if (squadra.equals(p.getAwayTeam())) {
                p.setAwayTeam(null);
            }
            partitaRepository.save(p);
        }

        if (squadra.getTornei() != null) {
            for (Torneo torneo : squadra.getTornei()) {
                torneo.getSquadre().remove(squadra);
            }
            squadra.getTornei().clear();
        }

        this.squadraRepository.delete(squadra);
    }
}