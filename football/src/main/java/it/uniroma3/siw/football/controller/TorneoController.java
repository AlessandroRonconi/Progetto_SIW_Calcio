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

import it.uniroma3.siw.football.exception.DuplicateTorneoException;
import it.uniroma3.siw.football.model.Squadra;
import it.uniroma3.siw.football.model.Torneo;
import it.uniroma3.siw.football.service.PartitaService;
import it.uniroma3.siw.football.service.SquadraService;
import it.uniroma3.siw.football.service.TorneoService;
import jakarta.validation.Valid;

@Controller

public class TorneoController {
    private final SquadraService squadraService;
    private final PartitaService partitaService;
    private final TorneoService torneoService;

    public TorneoController(TorneoService torneoService, PartitaService partitaService, SquadraService squadraService) {
        this.torneoService = torneoService;
        this.partitaService = partitaService;
        this.squadraService = squadraService;
    }

    @GetMapping("/tournaments")
    public String getTournamentList(Model model) {
        model.addAttribute("tournaments", this.torneoService.findAll());
        model.addAttribute("number", this.torneoService.count());
        return "/tournaments/list.html";
    }

    @GetMapping("/tournaments/{id}")
    public String getTournamentDetail(@PathVariable Long id, Model model) {
        model.addAttribute("tournament", this.torneoService.findById(id));
        return "/tournaments/show.html";
    }

    @GetMapping("/tournaments/{id}/participants")
    public String getParticipants(@PathVariable Long id, Model model) {
        Torneo t = this.torneoService.findById(id);
        model.addAttribute("tournament", t);
        model.addAttribute("participants", t.getSquadre());
        model.addAttribute("number", t.getSquadre().size());
        return "/tournaments/participants.html";
    }

    @GetMapping("/tournaments/{id}/calendar")
    public String getCalendar(@PathVariable Long id, Model model) {
        model.addAttribute("tournament", this.torneoService.findById(id));
        model.addAttribute("games", this.partitaService.gamesByTournament(id));
        return "/tournaments/calendar.html";
    }

    @GetMapping("/tournaments/{id}/classification")
    public String getClassification(@PathVariable Long id, Model model) {
        model.addAttribute("tournament", this.torneoService.findById(id));
        model.addAttribute("classificationRows", this.torneoService.getClassificationTable(id));

        return "tournaments/classification.html";
    }

    @GetMapping("/admin/tournaments/new")
    public String getTorneoForm(Model model) {
        Torneo tournament = new Torneo();
        tournament.setSquadre(new ArrayList<>());
        model.addAttribute("tournament", tournament);
        model.addAttribute("teams", squadraService.findAll());
        return "admin/tournaments/form";
    }

    @PostMapping("/admin/tournaments/new")
    public String postTorneoForm(@Valid @ModelAttribute("tournament") Torneo tournament,
            BindingResult bindingResult, Model model,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) Long teamId,
            @RequestParam(required = false) List<Long> teamIds) {

        List<Squadra> teams = new ArrayList<>();
        if (teamIds != null) {
            for (Long id : teamIds) {
                teams.add(squadraService.findById(id));
            }
        }

        tournament.setSquadre(teams);

        if (action != null && action.startsWith("removeTeam_")) {
            Long removeId = Long.valueOf(action.substring("removeTeam_".length()));
            tournament.getSquadre().removeIf(s -> s.getId().equals(removeId));
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        if ("addTeam".equals(action)) {
            if (teamId != null && teamId > 0) {
                Squadra team = squadraService.findById(teamId);
                if (!tournament.getSquadre().contains(team)) {
                    tournament.getSquadre().add(team);
                }
            }
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        try {
            this.torneoService.save(tournament);
        } catch (DuplicateTorneoException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        return "redirect:/tournaments";
    }

    @GetMapping("/admin/tournaments/{id}/edit")
    public String getTorneoEditForm(@PathVariable("id") Long id, Model model) {
        Torneo tournament = torneoService.findById(id);
        if (tournament.getSquadre() == null)
            tournament.setSquadre(new ArrayList<>());
        model.addAttribute("tournament", tournament);
        model.addAttribute("teams", squadraService.findAll());
        return "admin/tournaments/form";
    }

    @PostMapping("/admin/tournaments/{id}/edit")
    public String postTorneoEditForm(@PathVariable("id") Long id,
            @Valid @ModelAttribute("tournament") Torneo tournament,
            BindingResult bindingResult, Model model,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) Long teamId,
            @RequestParam(required = false) List<Long> teamIds) {

        List<Squadra> teams = new ArrayList<>();
        if (teamIds != null) {
            for (Long tId : teamIds) {
                teams.add(squadraService.findById(tId));
            }
        }
        tournament.setSquadre(teams);
        tournament.setId(id);

        if (action != null && action.startsWith("removeTeam_")) {
            Long removeId = Long.valueOf(action.substring("removeTeam_".length()));
            tournament.getSquadre().removeIf(s -> s.getId().equals(removeId));
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        if ("addTeam".equals(action)) {
            if (teamId != null && teamId > 0) {
                Squadra team = squadraService.findById(teamId);
                if (!tournament.getSquadre().contains(team)) {
                    tournament.getSquadre().add(team);
                }
            }
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        try {
            this.torneoService.save(tournament);
        } catch (DuplicateTorneoException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("teams", squadraService.findAll());
            return "admin/tournaments/form";
        }

        torneoService.save(tournament);
        return "redirect:/tournaments/" + id;
    }

}
