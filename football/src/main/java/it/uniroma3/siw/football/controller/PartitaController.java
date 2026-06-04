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

import it.uniroma3.siw.football.model.Commento;
import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.service.CommentoService;
import it.uniroma3.siw.football.service.PartitaService;
import jakarta.validation.Valid;

@Controller
public class PartitaController {
    private final PartitaService gameService;
    private final CommentoService commentoService;

    public PartitaController(PartitaService gameService, CommentoService commentoService) {
        this.gameService = gameService;
        this.commentoService = commentoService;
    }

    @GetMapping("/games/{id}")
    public String getPartitaDetail(@PathVariable Long id, Model model) {
        model.addAttribute("game", this.gameService.findById(id));
        return "/games/show";
    }

    @GetMapping("/games/{id}/comments")
    public String getCommentoi(@PathVariable Long id, Model model) {
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

    @PostMapping("/games/{gameId}/comments/{commentsd}/edit")
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

}
