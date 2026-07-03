package it.uniroma3.siw.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.DuplicateSquadraException;
import it.uniroma3.siw.football.exception.ResourceNotFoundException;
import it.uniroma3.siw.football.model.Giocatore;
import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.model.Torneo;
import it.uniroma3.siw.football.repository.GiocatoreRepository;
import it.uniroma3.siw.football.repository.PartitaRepository;
import it.uniroma3.siw.football.repository.SquadraRepository;

@Service
public class SquadraService {
    private final SquadraRepository squadraRepository;
    private final GiocatoreRepository giocatoreRepository;
    private final PartitaRepository partitaRepository;

    public SquadraService(SquadraRepository squadraRepository, GiocatoreRepository giocatoreRepository,
            PartitaRepository partitaRepository) {
        this.squadraRepository = squadraRepository;
        this.giocatoreRepository = giocatoreRepository;
        this.partitaRepository = partitaRepository;
    }

    @Transactional(readOnly = true)
    public List<Squadra> findAll() {
        List<Squadra> list = new ArrayList<>();
        this.squadraRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public void save(Squadra nuovaSquadra, List<Long> playerIds) {
        if (this.squadraRepository.existsByName(nuovaSquadra.getName())) {
            throw new DuplicateSquadraException("La squadra di nome '" + nuovaSquadra.getName() + "' esiste già");
        }

        // Inizializziamo l'array dei giocatori pulito
        nuovaSquadra.setGiocatori(new ArrayList<>());

        // Salviamo prima la squadra per generare l'ID (necessario se si usa Cascade o
        // l'helper)
        this.squadraRepository.save(nuovaSquadra);

        // Associazioni
        if (playerIds != null) {
            for (Long pId : playerIds) {
                Giocatore giocatore = giocatoreRepository.findById(pId)
                        .orElseThrow(() -> new ResourceNotFoundException("Giocatore non trovato"));
                nuovaSquadra.addGiocatore(giocatore);
            }
        }
    }

    @Transactional
    public Squadra findById(Long id) {
        return this.squadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Squadra non trovata."));
    }

    @Transactional
    public void update(Long id, Squadra datiForm, List<Long> playerIds) {
        Squadra squadraEsistente = this.findById(id);

        // Aggiorniamo i dati primitivi
        squadraEsistente.setName(datiForm.getName());
        squadraEsistente.setYearOfFoundation(datiForm.getYearOfFoundation());
        squadraEsistente.setCity(datiForm.getCity());

        // Ricostruiamo la lista dei desiderati dai passati dal form
        List<Giocatore> giocatoriDesiderati = new ArrayList<>();
        if (playerIds != null) {
            for (Long pId : playerIds) {
                giocatoriDesiderati.add(giocatoreRepository.findById(pId)
                        .orElseThrow(() -> new ResourceNotFoundException("Giocatore non trovato")));
            }
        }

        // 1. Rimuoviamo i giocatori non più presenti nel form
        List<Giocatore> daRimuovere = new ArrayList<>();
        for (Giocatore g : squadraEsistente.getGiocatori()) {
            if (!giocatoriDesiderati.contains(g)) {
                daRimuovere.add(g);
            }
        }
        for (Giocatore g : daRimuovere) {
            squadraEsistente.removeGiocatore(g);
        }

        // 2. Aggiungiamo i nuovi giocatori inseriti
        for (Giocatore g : giocatoriDesiderati) {
            if (!squadraEsistente.getGiocatori().contains(g)) {
                squadraEsistente.addGiocatore(g);
            }
        }

        // Hibernate rileverà le modifiche automaticamente a fine transazione,
        // ma fare un save esplicito rende il codice chiaro
        this.squadraRepository.save(squadraEsistente);
    }

    public Long count() {
        return this.squadraRepository.count();
    }

    @Transactional
    public void delete(Long id) {
        Squadra squadra = this.findById(id);

        // 1. Svincola i giocatori
        if (squadra.getGiocatori() != null) {
            List<Giocatore> giocatori = new ArrayList<>(squadra.getGiocatori());
            for (Giocatore g : giocatori) {
                squadra.removeGiocatore(g);
            }
        }

        // 2. Svincola le partite (Assicurati che questo metodo recuperi TUTTE le
        // partite della squadra, non solo quelle giocate)
        List<Partita> partite = partitaRepository.findByTeam(id);
        for (Partita p : partite) {
            // Confronto invertito sicuro contro i NullPointerException
            if (squadra.equals(p.getHomeTeam())) {
                p.setHomeTeam(null);
            }
            if (squadra.equals(p.getAwayTeam())) {
                p.setAwayTeam(null);
            }
            // Il save(p) qui non è strettamente necessario grazie a @Transactional,
            // ma se preferisci lasciarlo per chiarezza va bene lo stesso.
            partitaRepository.save(p);
        }

        // 3. Svincola i tornei
        if (squadra.getTornei() != null) {
            for (Torneo torneo : squadra.getTornei()) {
                // Rimuoviamo la squadra dal lato proprietario (la lista dentro Torneo)
                torneo.getSquadre().remove(squadra);
            }
            // Opzionale: puliamo anche la lista locale per buona pratica
            squadra.getTornei().clear();
        }

        // 4. Elimina la squadra
        this.squadraRepository.delete(squadra);
    }
}
