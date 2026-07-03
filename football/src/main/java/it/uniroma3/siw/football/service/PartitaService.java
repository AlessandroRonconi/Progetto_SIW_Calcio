package it.uniroma3.siw.football.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.DuplicatePartitaException;
import it.uniroma3.siw.football.exception.PartitaDataPostoConflictException;
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
        Long pId = game.getId();
        Long excludeId = pId != null ? pId : -1L;
        if (this.partitaRepository.existsByHomeTeamAndAwayTeamAndTorneoAndIdNot(
                game.getHomeTeam(), game.getAwayTeam(), game.getTorneo(), excludeId)) {
            throw new DuplicatePartitaException(
                    "La partita " + game.getHomeTeam().getName() + "-" + game.getAwayTeam().getName()
                            + " del torneo " + game.getTorneo().getName() + " esiste già");
        }
        this.checkOrariConflict(game.getDateTime(), game.getPlace(), excludeId);
        return this.partitaRepository.save(game);
    }

    private void checkOrariConflict(LocalDateTime dateTime, String place, Long excludeId) {
        List<Partita> conflitti = partitaRepository.findByDateTimeAndPlaceExcluding(dateTime, place, excludeId);
        if (!conflitti.isEmpty())
            throw new PartitaDataPostoConflictException(dateTime, place);
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

    @Transactional(readOnly = true)
    public List<Partita> findAll() {
        List<Partita> list = new ArrayList<>();
        this.partitaRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public void delete(Long id) {
        this.partitaRepository.deleteById(id);
    }
}
