package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Author;
import com.example.librarymanagementsystemnew.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/author") // Group all author-related routes
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute Author author) {
        author.setId(UUID.randomUUID().toString());

        authorService.createAuthor(author);
        return "redirect:/author";
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }
}