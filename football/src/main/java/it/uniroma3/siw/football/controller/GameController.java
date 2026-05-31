package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.football.model.Game;
import it.uniroma3.siw.football.service.GameService;

@Controller
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games/{id}")
    public String getGameDetail(@PathVariable Long id, Model model) {
        model.addAttribute("game", this.gameService.find(id));
        return "/games/show.html";
    }

    @GetMapping("/games/{id}/comments")
    public String getComments(@PathVariable Long id, Model model) {
        Game g = this.gameService.findById(id);
        model.addAttribute("game", g);
        model.addAttribute("comments", g.getComments());
        return "/games/comments.html";
    }
}
