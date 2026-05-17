package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.football.model.Team;
import it.uniroma3.siw.football.service.TeamService;

@Controller
@RequestMapping("/teams")
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/list")
    public String getTeamsList(Model model) {
        model.addAttribute("teams", this.teamService.findAll());
        return "/teams/list.html";
    }

    @GetMapping("/{id}")
    public String getTeamDetail(@PathVariable Long id, Model model) {
        Team t = this.teamService.findById(id);
        model.addAttribute("team", t);
        return "/teams/team.html";
    }
}
