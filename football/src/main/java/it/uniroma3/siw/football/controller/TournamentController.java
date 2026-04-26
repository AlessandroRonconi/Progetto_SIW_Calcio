package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.football.model.Tournament;
import it.uniroma3.siw.football.service.TournamentService;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String getTournamentList(Model model) {
        model.addAttribute("tournaments", tournamentService.findAll());
        return "tournaments.html";
    }

    @GetMapping("/{id}")
    public String getTournamentDetail(@PathVariable Long id, Model model) {
        Tournament t = this.tournamentService.findById(id);
        model.addAttribute("tournament", t);
        return "tournament.html";
    }

}
