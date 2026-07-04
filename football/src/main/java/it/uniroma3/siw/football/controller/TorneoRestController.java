package it.uniroma3.siw.football.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.football.dto.GameDTO;
import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.service.PartitaService;

@RestController
@RequestMapping("/rest/tournaments")
public class TorneoRestController {

    private final PartitaService partitaService;

    public TorneoRestController(PartitaService partitaService) {
        this.partitaService = partitaService;
    }

    @GetMapping("/{id}/calendar")
    public List<GameDTO> getCalendar(@PathVariable Long id) {
        List<Partita> games = this.partitaService.gamesByTournament(id);
        return games.stream()
                .map(g -> new GameDTO(
                        g.getId(),
                        g.getDateTime(),
                        g.getHomeTeam().getId(),
                        g.getHomeTeam().getName(),
                        g.getAwayTeam().getId(),
                        g.getAwayTeam().getName(),
                        g.getGoalsHome(),
                        g.getGoalsAway(),
                        g.getPlace(),
                        g.getStatus().name()))
                .collect(Collectors.toList());
    }
}