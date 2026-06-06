package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.football.model.Torneo;
import it.uniroma3.siw.football.service.PartitaService;
import it.uniroma3.siw.football.service.TorneoService;
import jakarta.validation.Valid;

@Controller

public class TorneoController {
    private final PartitaService partitaService;
    private final TorneoService torneoService;

    public TorneoController(TorneoService torneoService, PartitaService partitaService) {
        this.torneoService = torneoService;
        this.partitaService = partitaService;
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
        model.addAttribute("tournament", new Torneo());
        return "admin/tournaments/form";
    }

    @PostMapping("/admin/tournaments/new")
    public String postTorneoForm(@Valid @ModelAttribute("tournament") Torneo tournament, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors())
            return "admin/tournaments/form";
        this.torneoService.save(tournament);
        return "redirect:/tournaments";
    }

    @GetMapping("/admin/tournaments/{id}/edit")
    public String getTorneoEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tournament", torneoService.findById(id));
        return "admin/tournaments/editForm";
    }

    @PostMapping("/admin/tournaments/{id}/edit")
    public String postTorneoEditForm(@PathVariable("id") Long id,
            @Valid @ModelAttribute("tournament") Torneo tournament,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tournament", tournament);
            return "admin/tournaments/editForm";
        }
        torneoService.editTorneo(id, tournament.getName(), tournament.getYear(), tournament.getDescription());
        return "redirect:/tournaments/" + id;
    }

}
