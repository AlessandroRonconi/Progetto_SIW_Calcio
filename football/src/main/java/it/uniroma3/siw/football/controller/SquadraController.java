package it.uniroma3.siw.football.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.football.model.Giocatore;
import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.service.GiocatoreService;
import it.uniroma3.siw.football.service.SquadraService;
import jakarta.validation.Valid;

@Controller
public class SquadraController {
    private final GiocatoreService giocatoreService;
    private final SquadraService squadraService;

    public SquadraController(SquadraService squadraService, GiocatoreService giocatoreService) {
        this.squadraService = squadraService;
        this.giocatoreService = giocatoreService;
    }

    @GetMapping("/teams")
    public String getTeamsList(Model model) {
        model.addAttribute("teams", this.squadraService.findAll());
        model.addAttribute("number", this.squadraService.count());
        return "/teams/list.html";
    }

    @GetMapping("/teams/{id}")
    public String getTeamDetail(@PathVariable Long id, Model model) {
        Squadra t = this.squadraService.findById(id);
        model.addAttribute("team", t);
        model.addAttribute("players", t.getGiocatori());
        return "/teams/show.html";
    }

    @GetMapping("/admin/teams/new")
    public String getSquadraForm(Model model) {
        Squadra team = new Squadra();
        team.setGiocatori(new ArrayList<>());
        model.addAttribute("team", team);
        model.addAttribute("players", giocatoreService.findBySquadraIsNull());
        return "admin/teams/form";
    }

    @PostMapping("/admin/teams/new")
    public String postSquadraForm(@Valid @ModelAttribute("team") Squadra team, BindingResult bindingResult, Model model,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) Long playerId,
            @RequestParam(required = false) List<Long> playerIds) {
        List<Giocatore> players = new ArrayList<>();
        if (playerIds != null) {
            for (Long id : playerIds)
                players.add(giocatoreService.findById(id));
        }

        team.setGiocatori(players);

        if (action != null && action.startsWith("removePlayer_")) {
            Long removeId = Long.valueOf(action.substring("removePlayer_".length()));
            team.getGiocatori().removeIf(g -> g.getId().equals(removeId));
            model.addAttribute("players", giocatoreService.findBySquadraIsNull());
            return "admin/teams/form";
        }

        if ("addPlayer".equals(action)) {
            if (playerId != null && playerId > 0) {
                Giocatore player = giocatoreService.findById(playerId);
                if (!team.getGiocatori().contains(player)) {
                    team.getGiocatori().add(player);
                }
            }
            model.addAttribute("players", giocatoreService.findBySquadraIsNull());
            return "admin/teams/form";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("players", giocatoreService.findBySquadraIsNull());
            return "admin/teams/form";
        }
        this.squadraService.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/admin/teams/{id}/edit")
    public String getSquadraEditForm(@PathVariable("id") Long id, Model model) {
        Squadra team = squadraService.findById(id);
        if (team.getGiocatori() == null)
            team.setGiocatori(new ArrayList<>());
        model.addAttribute("team", team);
        model.addAttribute("players", giocatoreService.findBySquadraIsNull());
        return "admin/teams/form";
    }

    @PostMapping("/admin/teams/{id}/edit")
    public String postSquadraEditForm(@PathVariable("id") Long id,
            @Valid @ModelAttribute("team") Squadra team,
            BindingResult bindingResult, Model model,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) Long playerId,
            @RequestParam(required = false) List<Long> playerIds) {
        List<Giocatore> players = new ArrayList<>();
        if (playerIds != null) {
            for (Long pId : playerIds)
                players.add(giocatoreService.findById(pId));
        }

        team.setGiocatori(players);
        team.setId(id);

        if (action != null && action.startsWith("removePlayer_")) {
            Long removeId = Long.valueOf(action.substring("removePlayer_".length()));
            team.getGiocatori().removeIf(g -> g.getId().equals(removeId));
            model.addAttribute("players", giocatoreService.findBySquadraIsNull());
            return "admin/teams/form";
        }

        if ("addPlayer".equals(action)) {
            if (playerId != null && playerId > 0) {
                Giocatore player = giocatoreService.findById(playerId);
                if (!team.getGiocatori().contains(player)) {
                    team.getGiocatori().add(player);
                }
            }
            model.addAttribute("players", giocatoreService.findBySquadraIsNull());
            return "admin/teams/form";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("players", giocatoreService.findBySquadraIsNull());
            return "admin/teams/form";
        }
        this.squadraService.save(team);
        return "redirect:/teams";
    }
}
