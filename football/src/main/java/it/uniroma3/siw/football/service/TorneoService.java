package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.RigaClassifica;
import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.model.Torneo;
import it.uniroma3.siw.football.repository.PartitaRepository;
import it.uniroma3.siw.football.repository.TorneoRepository;

@Service
public class TorneoService {
    private final PartitaRepository partitaRepository;
    private final TorneoRepository tournamentRepository;

    public TorneoService(TorneoRepository tournamentRepository, PartitaRepository partitaRepository) {
        this.tournamentRepository = tournamentRepository;
        this.partitaRepository = partitaRepository;
    }

    @Transactional(readOnly = true)
    public List<Torneo> findAll() {
        List<Torneo> list = new ArrayList<>();
        this.tournamentRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public Torneo save(Torneo tournament) {
        return this.tournamentRepository.save(tournament);
    }

    @Transactional(readOnly = true)
    public Torneo findById(Long id) {
        return this.tournamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo non trovato."));
    }

    @Transactional
    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }

    @Transactional
    public Long count() {
        return this.tournamentRepository.count();
    }

    @Transactional(readOnly = true)
    public List<RigaClassifica> getClassificationTable(Long tournamentId) {
        Torneo tournament = this.findById(tournamentId);
        if (tournament == null)
            return new ArrayList<>();

        // Prende i team partecipanti
        List<Squadra> teams = tournament.getSquadre();

        List<RigaClassifica> righe = new ArrayList<>();

        for (Squadra team : teams) {
            // Filtra solo i match giocati da questa squadra
            righe.add(new RigaClassifica(team, partitaRepository.findPlayedByTeam(team.getId())));
        }

        // Ordina per Punti (e Differenza Reti come secondo criterio)
        righe.sort((r1, r2) -> {
            int res = Integer.compare(r2.getPoints(), r1.getPoints());
            if (res == 0) {
                return Integer.compare(r2.getGoalsDifference(), r1.getGoalsDifference());
            }
            return res;
        });

        return righe;
    }

    @Transactional
    public void editTorneo(Long torneoId, String nome, Long anno, String descrizione) {
        Torneo torneo = this.findById(torneoId);
        torneo.setName(nome);
        torneo.setYear(anno);
        torneo.setDescription(descrizione);
        this.save(torneo);
    }

}
