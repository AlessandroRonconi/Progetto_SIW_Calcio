package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.repository.SquadraRepository;

@Service
public class SquadraService {
    private SquadraRepository squadraRepository;

    public SquadraService(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    @Transactional(readOnly = true)
    public List<Squadra> findAll() {
        List<Squadra> list = new ArrayList<>();
        this.squadraRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public Squadra save(Squadra t) {
        return this.squadraRepository.save(t);
    }

    @Transactional
    public Squadra findById(Long id) {
        return this.squadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Squadra non trovata."));
    }

}
