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

    // Inject the AuthorService (as per )
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * GET /author
     * Shows the complete list of authors. [cite: 64, 65]
     */
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author/index"; // Maps to templates/author/index.html [cite: 82]
    }

    /**
     * GET /author/new
     * Shows the form for creating a new author. [cite: 66]
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author()); // Add empty object for form data [cite: 83]
        return "author/form"; // Maps to templates/author/form.html
    }

    /**
     * POST /author
     * Processes the form and creates the new author. [cite: 66]
     */
    @PostMapping
    public String createAuthor(@ModelAttribute Author author) {
        // Your InMemoryRepository needs an ID, so we generate one.
        author.setId(UUID.randomUUID().toString());

        authorService.createAuthor(author);
        return "redirect:/author"; // Redirect to the list view
    }

    /**
     * POST /author/{id}/delete
     * Deletes the chosen author. [cite: 67, 68]
     */
    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return "redirect:/author"; // Redirect to the list view
    }
}