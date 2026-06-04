package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.service.SquadraService;

@Controller
@RequestMapping("/teams")
public class SquadraController {
    private final SquadraService squadraService;

    public SquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @GetMapping("/list")
    public String getTeamsList(Model model) {
        model.addAttribute("teams", this.squadraService.findAll());
        return "/teams/list.html";
    }

    @GetMapping("/{id}")
    public String getTeamDetail(@PathVariable Long id, Model model) {
        Squadra t = this.squadraService.findById(id);
        model.addAttribute("team", t);
        return "/teams/show.html";
    }
}
