package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.TournamentNotFoundException;
import it.uniroma3.siw.football.model.Tournament;
import it.uniroma3.siw.football.repository.TournamentRepository;

@Service
public class TournamentService {
    private TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional(readOnly = true)
    public List<Tournament> findAll() {
        List<Tournament> list = new ArrayList<>();
        this.tournamentRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public Tournament save(Tournament tournament) {
        return this.tournamentRepository.save(tournament);
    }

    @Transactional(readOnly = true)
    public Tournament findById(Long id) {
        return this.tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Long count() {
        return this.tournamentRepository.count();
    }

}
