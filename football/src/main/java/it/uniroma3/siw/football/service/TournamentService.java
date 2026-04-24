package it.uniroma3.siw.football.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.model.Tournament;
import it.uniroma3.siw.football.repository.TournamentRepository;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Transactional(readOnly = true)
    public List<Tournament> findAll() {
        return (List<Tournament>) this.tournamentRepository.findAll();
    }

    @Transactional
    public Tournament save(Tournament tournament) {
        return this.tournamentRepository.save(tournament);
    }

    @Transactional(readOnly = true)
    public Tournament findById(Long id) {
        return this.tournamentRepository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }

}
