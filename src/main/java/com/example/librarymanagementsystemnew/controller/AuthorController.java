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
        model.addAttribute("pageTitle", "Create New Author");
        return "author/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
        model.addAttribute("author", author);
        model.addAttribute("pageTitle", "Edit Author");
        return "author/form";
    }

    @PostMapping
    public String saveAuthor(@ModelAttribute Author author) {
        if (author.getId() == null || author.getId().isEmpty()) {
            author.setId(UUID.randomUUID().toString());
            authorService.createAuthor(author);
        } else {
            authorService.updateAuthor(author);
        }
        return "redirect:/author";
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }
}