package it.uniroma3.siw.football.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.model.enums.PartitaStatus;
import it.uniroma3.siw.football.repository.PartitaRepository;

@Service
public class PartitaService {

    private final PartitaRepository partitaRepository;

    public PartitaService(PartitaRepository partitaRepository) {
        this.partitaRepository = partitaRepository;
    }

    @Transactional
    public Partita findById(Long id) {
        return this.partitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partita non trovata"));
    }

    @Transactional
    public Partita save(Partita game) {
        return this.partitaRepository.save(game);
    }

    @Transactional(readOnly = true)
    public List<Partita> gamesByTournament(Long tId) {
        return partitaRepository.findByTournamentFetchTeams(tId);
    }

    @Transactional
    public void updateResult(Long id, int home, int away) {
        Partita p = partitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partita non trovata."));

        p.setGoalsHome(home);
        p.setGoalsAway(away);
        p.setStatus(PartitaStatus.PLAYED);
    }
}
