package it.uniroma3.siw.football.controller;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.football.exception.DuplicatePartitaException;
import it.uniroma3.siw.football.exception.PartitaDataPostoConflictException;
import it.uniroma3.siw.football.model.Commento;
import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.model.enums.PartitaStatus;
import it.uniroma3.siw.football.service.ArbitroService;
import it.uniroma3.siw.football.service.CommentoService;
import it.uniroma3.siw.football.service.PartitaService;
import it.uniroma3.siw.football.service.SquadraService;
import it.uniroma3.siw.football.service.TorneoService;
import jakarta.validation.Valid;

@Controller
public class PartitaController {
    private final PartitaService gameService;
    private final CommentoService commentoService;
    private final SquadraService squadraService;
    private final TorneoService torneoService;
    private final ArbitroService arbitroService;

    public PartitaController(PartitaService gameService, CommentoService commentoService,
            SquadraService squadraService, TorneoService torneoService, ArbitroService arbitroService) {
        this.gameService = gameService;
        this.commentoService = commentoService;
        this.squadraService = squadraService;
        this.torneoService = torneoService;
        this.arbitroService = arbitroService;
    }

    @GetMapping("/games")
    public String getPartiteList(Model model) {
        model.addAttribute("games", this.gameService.findAll());
        return "games/list";
    }

    @GetMapping("/games/{id}")
    public String getPartitaDetail(@PathVariable Long id, Model model) {
        model.addAttribute("game", this.gameService.findById(id));
        return "/games/show";
    }

    @GetMapping("/games/{id}/comments")
    public String getCommenti(@PathVariable Long id, Model model) {
        Partita g = this.gameService.findById(id);
        model.addAttribute("game", g);
        model.addAttribute("comments", g.getCommenti());
        return "/games/comments";
    }

    @GetMapping("/games/{id}/comments/new")
    public String createCommento(@PathVariable("id") Long id, Model model) {
        model.addAttribute("comment", new Commento());
        model.addAttribute("game", this.gameService.findById(id));
        return "/comments/form";
    }

    @PostMapping("/games/{id}/comments/new")
    public String newCommento(@PathVariable("id") Long id, @Valid @ModelAttribute("comment") Commento comment,
            BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("game", this.gameService.findById(id));
            return "comments/form";
        }
        this.commentoService.createCommento(id, userDetails.getUsername(), comment.getText());
        return "redirect:/games/" + id + "/comments";
    }

    @GetMapping("/games/{gameId}/comments/{commentId}/edit")
    public String editCommento(@PathVariable("gameId") Long gameId, @PathVariable("commentId") Long commentId,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Commento comment = this.commentoService.findById(commentId);
        if (commentoService.isNotOwner(comment, userDetails.getUsername())) {
            return "redirect:/games/" + gameId + "/comments";
        }
        model.addAttribute("comment", comment);
        model.addAttribute("gameId", gameId);
        return "comments/editForm";
    }

    @PostMapping("/games/{gameId}/comments/{commentId}/edit")
    public String editedCommento(@PathVariable("gameId") Long gameId, @PathVariable("commentId") Long commentId,
            @Valid @ModelAttribute("comment") Commento commentForm, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("gameId", gameId);
            model.addAttribute("comment", commentForm);
            return "comments/editForm";
        }
        Commento commentOld = this.commentoService.findById(commentId);
        try {
            commentoService.checkOwner(commentOld, userDetails.getUsername());
        } catch (RuntimeException e) {
            return "redirect:/games/" + gameId + "/comments";
        }
        commentOld.setText(commentForm.getText());
        commentOld.setDateTime(LocalDateTime.now());
        commentoService.save(commentOld);
        return "redirect:/games/" + gameId + "/comments";
    }

    @GetMapping("/admin/games/new")
    public String getPartitaForm(Model model) {
        Partita game = new Partita();
        game.setStatus(PartitaStatus.SCHEDULED);
        model.addAttribute("game", game);
        addFormAttributes(model);
        return "admin/games/form";
    }

    @GetMapping("/admin/games/{id}/edit")
    public String getPartitaEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", this.gameService.findById(id));
        addFormAttributes(model);
        return "admin/games/form";
    }

    @PostMapping("/admin/games/new")
    public String postPartitaForm(@Valid @ModelAttribute("game") Partita game,
            BindingResult bindingResult, Model model,
            @RequestParam(required = false) Long torneoId,
            @RequestParam(required = false) Long homeTeamId,
            @RequestParam(required = false) Long awayTeamId,
            @RequestParam(required = false) Long arbitroId) {

        game.setStatus(PartitaStatus.SCHEDULED);
        popolaRelazioni(game, torneoId, homeTeamId, awayTeamId, arbitroId, bindingResult);

        if (bindingResult.hasErrors()) {
            addFormAttributes(model);
            return "admin/games/form";
        }

        try {
            this.gameService.save(game);
        } catch (DuplicatePartitaException | PartitaDataPostoConflictException e) {
            model.addAttribute("globalError", e.getMessage());
            addFormAttributes(model);
            return "admin/games/form";
        }

        return "redirect:/games/" + game.getId();
    }

    @PostMapping("/admin/games/{id}/edit")
    public String postPartitaEditForm(@PathVariable("id") Long id,
            @Valid @ModelAttribute("game") Partita game,
            BindingResult bindingResult, Model model,
            @RequestParam(required = false) Long torneoId,
            @RequestParam(required = false) Long homeTeamId,
            @RequestParam(required = false) Long awayTeamId,
            @RequestParam(required = false) Long arbitroId) {

        game.setId(id);
        popolaRelazioni(game, torneoId, homeTeamId, awayTeamId, arbitroId, bindingResult);

        if (bindingResult.hasErrors()) {
            addFormAttributes(model);
            return "admin/games/form";
        }

        try {
            this.gameService.save(game);
        } catch (DuplicatePartitaException | PartitaDataPostoConflictException e) {
            model.addAttribute("globalError", e.getMessage());
            addFormAttributes(model);
            return "admin/games/form";
        }

        return "redirect:/games/" + id;
    }

    private void addFormAttributes(Model model) {
        model.addAttribute("tournaments", torneoService.findAll());
        model.addAttribute("teams", squadraService.findAll());
        model.addAttribute("arbitri", arbitroService.findAll());
        model.addAttribute("statuses", PartitaStatus.values());
    }

    private void popolaRelazioni(Partita game, Long torneoId, Long homeTeamId, Long awayTeamId, Long arbitroId,
            BindingResult bindingResult) {

        if (torneoId == null) {
            bindingResult.rejectValue("torneo", "torneo.required", "Il torneo è obbligatorio");
        } else {
            game.setTorneo(torneoService.findById(torneoId));
        }

        if (homeTeamId == null) {
            bindingResult.rejectValue("homeTeam", "homeTeam.required", "La squadra di casa è obbligatoria");
        } else {
            game.setHomeTeam(squadraService.findById(homeTeamId));
        }

        if (awayTeamId == null) {
            bindingResult.rejectValue("awayTeam", "awayTeam.required", "La squadra ospite è obbligatoria");
        } else {
            game.setAwayTeam(squadraService.findById(awayTeamId));
        }

        if (homeTeamId != null && awayTeamId != null && homeTeamId.equals(awayTeamId)) {
            bindingResult.rejectValue("awayTeam", "awayTeam.same",
                    "La squadra ospite deve essere diversa da quella di casa");
        }

        game.setArbitro(arbitroId != null ? arbitroService.findById(arbitroId) : null);
    }

    @PostMapping("/admin/games/{id}/delete")
    public String deletePartita(@PathVariable("id") Long id) {
        this.gameService.delete(id);
        return "redirect:/games";
    }
}