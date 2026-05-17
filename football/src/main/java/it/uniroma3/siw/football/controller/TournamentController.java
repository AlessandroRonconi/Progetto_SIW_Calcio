package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.football.model.Tournament;
import it.uniroma3.siw.football.service.GameService;
import it.uniroma3.siw.football.service.TournamentService;

@Controller

public class TournamentController {
    private GameService gameService;
    private TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService, GameService gameService) {
        this.tournamentService = tournamentService;
        this.gameService = gameService;
    }

    @GetMapping("/tournaments")
    public String getTournamentList(Model model) {
        model.addAttribute("tournaments", this.tournamentService.findAll());
        model.addAttribute("number", this.tournamentService.count());
        return "/tournaments/list.html";
    }

    @GetMapping("/tournaments/{id}")
    public String getTournamentDetail(@PathVariable Long id, Model model) {
        model.addAttribute("tournament", this.tournamentService.findById(id));
        return "/tournaments/tournament.html";
    }

    @GetMapping("/tournaments/{id}/participants")
    public String getParticipants(@PathVariable Long id, Model model) {
        Tournament t = this.tournamentService.findById(id);
        model.addAttribute("tournament", t);
        model.addAttribute("participants", t.getTeams());
        return "/tournaments/participants.html";
    }

    @GetMapping("/tournaments/{id}/calendar")
    public String getCalendar(@PathVariable Long id, Model model) {
        model.addAttribute("tournament", this.tournamentService.findById(id));
        model.addAttribute("games", this.gameService.gamesByTournament(id));
        return "/tournaments/calendar.html";
    }

}
