package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.football.exception.DuplicateGiocatoreException;
import it.uniroma3.siw.football.model.Giocatore;
import it.uniroma3.siw.football.service.GiocatoreService;
import it.uniroma3.siw.football.service.SquadraService;
import jakarta.validation.Valid;

@Controller
public class GiocatoreController {

    private final GiocatoreService giocatoreService;
    private final SquadraService squadraService;

    public GiocatoreController(GiocatoreService giocatoreService, SquadraService squadraService) {
        this.giocatoreService = giocatoreService;
        this.squadraService = squadraService;
    }

    @GetMapping("/players")
    public String getPlayersList(Model model) {
        model.addAttribute("players", this.giocatoreService.findAll());
        return "players/list";
    }

    @GetMapping("/admin/players/new")
    public String getGiocatoreForm(Model model) {
        model.addAttribute("player", new Giocatore());
        model.addAttribute("squadre", this.squadraService.findAll());
        return "admin/players/form";
    }

    @PostMapping("/admin/players/new")
    public String postGiocatoreForm(@Valid @ModelAttribute("player") Giocatore player,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("squadre", this.squadraService.findAll());
            return "admin/players/form";
        }
        try {
            this.giocatoreService.save(player);
        } catch (DuplicateGiocatoreException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("squadre", this.squadraService.findAll());
            return "admin/players/form";
        }
        return "redirect:/players";
    }

    @GetMapping("/admin/players/{id}/edit")
    public String getGiocatoreEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("player", this.giocatoreService.findById(id));
        model.addAttribute("squadre", this.squadraService.findAll());
        return "admin/players/form";
    }

    @PostMapping("/admin/players/{id}/edit")
    public String postGiocatoreEditForm(@PathVariable Long id,
            @Valid @ModelAttribute("player") Giocatore player,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("squadre", this.squadraService.findAll());
            return "admin/players/form";
        }
        player.setId(id);
        try {
            this.giocatoreService.save(player);
        } catch (DuplicateGiocatoreException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("squadre", this.squadraService.findAll());
            return "admin/players/form";
        }
        return "redirect:/players";
    }

    @PostMapping("/admin/players/{id}/delete")
    public String deletePlayer(@PathVariable Long id) {
        this.giocatoreService.delete(id);
        return "redirect:/players";
    }
}