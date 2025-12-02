package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.BookDetails;
import com.example.librarymanagementsystemnew.service.AuthorService;
import com.example.librarymanagementsystemnew.service.BookDetailsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookdetails")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;
    private final AuthorService authorService;

    public BookDetailsController(BookDetailsService bookDetailsService, AuthorService authorService) {
        this.bookDetailsService = bookDetailsService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBookDetails(Model model) {
        model.addAttribute("bookDetailsList", bookDetailsService.getAllBooks());
        return "bookdetails/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookDetails", new BookDetails());
        model.addAttribute("allAuthors", authorService.getAllAuthors()); // Populate Dropdown
        model.addAttribute("pageTitle", "Create New Book");
        return "bookdetails/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookDetails bd = bookDetailsService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("bookDetails", bd);
        model.addAttribute("allAuthors", authorService.getAllAuthors()); // Populate Dropdown
        model.addAttribute("pageTitle", "Edit Book");
        return "bookdetails/form";
    }

    @PostMapping
    public String saveBookDetails(@Valid @ModelAttribute BookDetails bookDetails, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Reload list on error so form doesn't break
            model.addAttribute("allAuthors", authorService.getAllAuthors());
            model.addAttribute("pageTitle", bookDetails.getId() == null ? "Create New Book" : "Edit Book");
            return "bookdetails/form";
        }

        if (bookDetails.getId() == null) {
            bookDetailsService.createBook(bookDetails);
        } else {
            bookDetailsService.updateBook(bookDetails);
        }
        return "redirect:/bookdetails";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookDetails(@PathVariable Long id) {
        bookDetailsService.deleteBook(id);
        return "redirect:/bookdetails";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        BookDetails bookDetails = bookDetailsService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", bookDetails);
        return "bookDetails/details";
    }
}