package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Library;
import com.example.librarymanagementsystemnew.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listLibraries(Model model) {
        model.addAttribute("libraries", libraryService.getAllLibraries());
        return "library/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("library", new Library());
        model.addAttribute("pageTitle", "Create New Library");
        return "library/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Library lib = libraryService.getLibraryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid library Id:" + id));
        model.addAttribute("library", lib);
        model.addAttribute("pageTitle", "Edit Library");
        return "library/form";
    }

    @PostMapping
    public String saveLibrary(@ModelAttribute Library library, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", library.getId() == null ? "Create New Library" : "Edit Library");
            return "library/form";
        }

        if (library.getId() == null) {
            libraryService.createLibrary(library);
        } else {
            libraryService.updateLibrary(library);
        }
        return "redirect:/library";
    }

    @PostMapping("/{id}/delete")
    public String deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return "redirect:/library";
    }
}
