package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.BookDetails;
import com.example.librarymanagementsystemnew.service.BookDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookdetails")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;

    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping
    public String listBookDetails(Model model) {
        model.addAttribute("bookDetailsList", bookDetailsService.findAll());
        return "bookdetails/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookDetails", new BookDetails());
        model.addAttribute("pageTitle", "Create New Book");
        return "bookdetails/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        BookDetails bd = bookDetailsService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("bookDetails", bd);
        model.addAttribute("pageTitle", "Edit Book");
        return "bookdetails/form";
    }

    @PostMapping
    public String saveBookDetails(@ModelAttribute BookDetails bookDetails) {
        if (bookDetails.getId() == null || bookDetails.getId().isEmpty()) {
            bookDetailsService.create(bookDetails);
        } else {
            bookDetailsService.update(bookDetails);
        }
        return "redirect:/bookdetails";
    }


    @PostMapping("/{id}/delete")
    public String deleteBookDetails(@PathVariable String id) {
        bookDetailsService.delete(id);
        return "redirect:/bookdetails";
    }
}