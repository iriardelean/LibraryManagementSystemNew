package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Library;
import com.example.librarymanagementsystemnew.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listLibraries(Model model,
                                @RequestParam(required = false) String name,
                                @RequestParam(defaultValue = "id") String sortField,
                                @RequestParam(defaultValue = "asc") String sortDir) {

        model.addAttribute("libraries", libraryService.searchLibraries(name, sortField, sortDir));

        model.addAttribute("name", name);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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
    public String saveLibrary(@Valid @ModelAttribute Library library, BindingResult result, Model model) {
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

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        Library library = libraryService.getLibraryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid library Id:" + id));
        model.addAttribute("library", library);
        return "library/details";
    }
}