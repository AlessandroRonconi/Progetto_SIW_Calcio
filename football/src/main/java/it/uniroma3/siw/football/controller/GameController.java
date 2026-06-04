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

import it.uniroma3.siw.football.model.Comment;
import it.uniroma3.siw.football.model.Game;
import it.uniroma3.siw.football.service.CommentService;
import it.uniroma3.siw.football.service.GameService;
import jakarta.validation.Valid;

@Controller
public class GameController {
    private final GameService gameService;
    private final CommentService commentService;

    public GameController(GameService gameService, CommentService commentService) {
        this.gameService = gameService;
        this.commentService = commentService;
    }

    @GetMapping("/games/{id}")
    public String getGameDetail(@PathVariable Long id, Model model) {
        model.addAttribute("game", this.gameService.findById(id));
        return "/games/show";
    }

    @GetMapping("/games/{id}/comments")
    public String getComments(@PathVariable Long id, Model model) {
        Game g = this.gameService.findById(id);
        model.addAttribute("game", g);
        model.addAttribute("comments", g.getComments());
        return "/games/comments";
    }

    @GetMapping("/games/{id}/comments/new")
    public String createComment(@PathVariable("id") Long id, Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("game", this.gameService.findById(id));
        return "/comments/form";
    }

    @PostMapping("/games/{id}/comments/new")
    public String newComment(@PathVariable("id") Long id, @Valid @ModelAttribute("comment") Comment comment,
            BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("game", this.gameService.findById(id));
            return "comments/form";
        }
        this.commentService.createComment(id, userDetails.getUsername(), comment.getText());
        return "redirect:/games/" + id + "/comments";
    }

    @GetMapping("/games/{gameId}/comments/{commentId}/edit")
    public String editComment(@PathVariable("gameId") Long gameId, @PathVariable("commentId") Long commentId,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Comment comment = this.commentService.findById(commentId);
        if (commentService.isNotOwner(comment, userDetails.getUsername())) {
            return "redirect:/games/" + gameId + "/comments";
        }
        model.addAttribute("comment", comment);
        model.addAttribute("gameId", gameId);
        return "comments/editForm";
    }

    @PostMapping("/games/{gameId}/comments/{commentsd}/edit")
    public String editedComment(@PathVariable("gameId") Long gameId, @PathVariable("commentId") Long commentId,
            @Valid @ModelAttribute("comment") Comment commentForm, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("gameId", gameId);
            model.addAttribute("comment", commentForm);
            return "comments/editForm";
        }
        Comment commentOld = this.commentService.findById(commentId);
        try {
            commentService.checkOwner(commentOld, userDetails.getUsername());
        } catch (RuntimeException e) {
            return "redirect:/games/" + gameId + "/comments";
        }
        commentOld.setText(commentForm.getText());
        commentOld.setDateTime(LocalDateTime.now());
        commentService.save(commentOld);
        return "redirect:/games/" + gameId + "/comments";
    }

}
