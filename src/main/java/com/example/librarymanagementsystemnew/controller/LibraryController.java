package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Library;
import com.example.librarymanagementsystemnew.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("libraries", libraryService.findAll());
        return "library/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("library", new Library(null, null, new ArrayList<>(), new ArrayList<>()));
        model.addAttribute("pageTitle", "Create New Library");
        return "library/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Library lib = libraryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid library Id:" + id));
        model.addAttribute("library", lib);
        model.addAttribute("pageTitle", "Edit Library");
        return "library/form";
    }

    @PostMapping
    public String saveLibrary(@ModelAttribute Library library) {
        if (library.getId() == null || library.getId().isEmpty()) {
            libraryService.create(library);
        } else {
            libraryService.update(library);
        }
        return "redirect:/library";
    }

    @PostMapping("/{id}/delete")
    public String deleteLibrary(@PathVariable String id) {
        libraryService.delete(id);
        return "redirect:/library";
    }
}
