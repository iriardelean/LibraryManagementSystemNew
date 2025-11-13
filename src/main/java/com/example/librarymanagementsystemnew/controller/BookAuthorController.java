package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.BookAuthor;
import com.example.librarymanagementsystemnew.service.BookAuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookauthor")
public class BookAuthorController {

    private final BookAuthorService bookAuthorService;

    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping
    public String listBookAuthors(Model model) {
        model.addAttribute("bookAuthors", bookAuthorService.findAll());
        return "bookauthor/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookAuthor", new BookAuthor());
        model.addAttribute("pageTitle", "Create New Book-Author Link");
        return "bookauthor/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        BookAuthor bookAuthor = bookAuthorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BookAuthor Id:" + id));
        model.addAttribute("bookAuthor", bookAuthor);
        model.addAttribute("pageTitle", "Edit Book-Author Link");
        return "bookauthor/form";
    }

    @PostMapping
    public String saveBookAuthor(@ModelAttribute BookAuthor bookAuthor) {
        if (bookAuthor.getId() == null || bookAuthor.getId().isEmpty()) {
            bookAuthorService.create(bookAuthor);
        } else {
            bookAuthorService.update(bookAuthor);
        }
        return "redirect:/bookauthor";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookAuthor(@PathVariable String id) {
        bookAuthorService.delete(id);
        return "redirect:/bookauthor";
    }
}