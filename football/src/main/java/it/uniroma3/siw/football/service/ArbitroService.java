package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Arbitro;
import it.uniroma3.siw.football.repository.ArbitroRepository;

@Service
public class ArbitroService {
    private final ArbitroRepository arbitroRepository;

    public ArbitroService(ArbitroRepository arbitroRepository) {
        this.arbitroRepository = arbitroRepository;
    }

    @Transactional(readOnly = true)
    public List<Arbitro> findAll() {
        List<Arbitro> list = new ArrayList<>();
        this.arbitroRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional(readOnly = true)
    public Arbitro findById(Long id) {
        return this.arbitroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbitro non trovato."));
    }
}