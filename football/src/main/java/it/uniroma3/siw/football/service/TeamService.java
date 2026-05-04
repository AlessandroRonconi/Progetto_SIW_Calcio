package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.TeamNotFoundException;
import it.uniroma3.siw.football.model.Team;
import it.uniroma3.siw.football.repository.TeamRepository;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public List<Team> findAll() {
        List<Team> list = new ArrayList<>();
        this.teamRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public Team save(Team t) {
        return this.teamRepository.save(t);
    }

    @Transactional
    public Team findById(Long id) {
        return this.teamRepository.findById(id).orElseThrow(()->new TeamNotFoundException(id));
    }

}
