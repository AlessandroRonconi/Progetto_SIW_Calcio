package it.uniroma3.siw.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.football.service.CommentService;

@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{id}")
    public String getMethodName(Long id, Model model) {
        model.addAttribute("comment", this.commentService.findById(id));
        return "/comments/show.html";
    }

    @PostMapping("/comments/{id}/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("comment", this.commentService.findById(id));
        return "/comments/edit.html";
    }
}
