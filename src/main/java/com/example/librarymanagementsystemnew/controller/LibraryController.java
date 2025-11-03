package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Library;
import com.example.librarymanagementsystemnew.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
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
        return "library/form";
    }

    @PostMapping
    public String createLibrary(@RequestParam String name) {
        Library lib = new Library(UUID.randomUUID().toString(), name, new ArrayList<>(), new ArrayList<>());
        libraryService.create(lib);
        return "redirect:/library";
    }

    @PostMapping("/{id}/delete")
    public String deleteLibrary(@PathVariable String id) {
        libraryService.delete(id);
        return "redirect:/library";
    }
}
