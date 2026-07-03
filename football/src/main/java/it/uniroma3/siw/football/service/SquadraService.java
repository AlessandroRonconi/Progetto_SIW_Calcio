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
    private final SquadraRepository squadraRepository;

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
    public void save(Squadra t) {
        this.squadraRepository.save(t);
    }

    @Transactional
    public Squadra findById(Long id) {
        return this.squadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Squadra non trovata."));
    }

    @Transactional
    public void editSquadra(Long squadraId, String nome, Long anno, String citta) {
        Squadra squadra = this.findById(squadraId);
        squadra.setName(nome);
        squadra.setYearOfFoundation(anno);
        squadra.setCity(citta);
        this.save(squadra);
    }

    public Long count() {
        return this.squadraRepository.count();
    }
}
