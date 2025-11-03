package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.BookDetails;
import com.example.librarymanagementsystemnew.service.BookDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        return "bookdetails/form";
    }

    @PostMapping
    public String createBookDetails(@ModelAttribute BookDetails bookDetails) {
        bookDetails.setId(UUID.randomUUID().toString());
        bookDetailsService.create(bookDetails);
        return "redirect:/bookdetails";
    }


    @PostMapping("/{id}/delete")
    public String deleteBookDetails(@PathVariable String id) {
        bookDetailsService.delete(id);
        return "redirect:/bookdetails";
    }
}