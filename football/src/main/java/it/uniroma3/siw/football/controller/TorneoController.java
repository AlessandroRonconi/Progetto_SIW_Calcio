package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.football.model.Torneo;
import it.uniroma3.siw.football.service.PartitaService;
import it.uniroma3.siw.football.service.TorneoService;

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

}
