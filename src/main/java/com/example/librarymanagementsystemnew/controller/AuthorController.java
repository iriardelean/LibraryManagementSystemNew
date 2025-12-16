package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Author;
import com.example.librarymanagementsystemnew.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String genre,
                              @RequestParam(required = false) String period,
                              @RequestParam(defaultValue = "id") String sortField,
                              @RequestParam(defaultValue = "asc") String sortDir) {

        model.addAttribute("authors", authorService.searchAuthors(name, genre, period, sortField, sortDir));

        model.addAttribute("name", name);
        model.addAttribute("genre", genre);
        model.addAttribute("period", period);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "author/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("pageTitle", "Create New Author");
        return "author/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
        model.addAttribute("author", author);
        model.addAttribute("pageTitle", "Edit Author");
        return "author/form";
    }

    @PostMapping
    public String saveAuthor(@Valid @ModelAttribute Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", author.getId() == null ? "Create New Author" : "Edit Author");
            return "author/form";
        }

        if (author.getId() == null) {
            authorService.createAuthor(author);
        } else {
            authorService.updateAuthor(author);
        }
        return "redirect:/author";
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
        model.addAttribute("author", author);
        return "author/details";
    }
}