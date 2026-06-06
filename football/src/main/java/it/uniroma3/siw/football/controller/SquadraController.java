package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.service.SquadraService;
import jakarta.validation.Valid;

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
        model.addAttribute("players", t.getGiocatori());
        return "/teams/show.html";
    }

    @GetMapping("/admin/teams/new")
    public String getSquadraForm(Model model) {
        model.addAttribute("team", new Squadra());
        return "admin/teams/form";
    }

    @PostMapping("/admin/teams/new")
    public String postSquadraForm(@Valid @ModelAttribute("team") Squadra team, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors())
            return "admin/teams/form";
        this.squadraService.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/admin/teams/{id}/edit")
    public String getSquadraEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("team", squadraService.findById(id));
        return "admin/teams/editForm";
    }

    @PostMapping("/admin/teams/{id}/edit")
    public String postSquadraEditForm(@PathVariable("id") Long id,
            @Valid @ModelAttribute("team") Squadra team,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("team", team);
            return "admin/teams/editForm";
        }
        squadraService.editSquadra(id, team.getName(), team.getYearOfFoundation(), team.getCity());
        return "redirect:/teams/" + id;
    }
}
