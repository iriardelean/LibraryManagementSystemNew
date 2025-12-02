package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.MagazineDetails;
import com.example.librarymanagementsystemnew.service.MagazineDetailsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/magazinedetails")
public class MagazineDetailsController {

    private final MagazineDetailsService magazineDetailsService;

    public MagazineDetailsController(MagazineDetailsService magazineDetailsService) {
        this.magazineDetailsService = magazineDetailsService;
    }

    @GetMapping
    public String listMagazineDetails(Model model) {
        model.addAttribute("magazineDetailsList", magazineDetailsService.getAllMagazines());
        return "magazinedetails/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("magazineDetails", new MagazineDetails());
        model.addAttribute("pageTitle", "Create New Magazine");
        return "magazinedetails/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        MagazineDetails md = magazineDetailsService.getMagazineById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid magazine Id:" + id));
        model.addAttribute("magazineDetails", md);
        model.addAttribute("pageTitle", "Edit Magazine");
        return "magazinedetails/form";
    }

    @PostMapping
    public String saveMagazineDetails(@Valid @ModelAttribute MagazineDetails magazineDetails, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", magazineDetails.getId() == null ? "Create New Magazine" : "Edit Magazine");
            return "magazinedetails/form";
        }

        if (magazineDetails.getId() == null) {
            magazineDetailsService.createMagazine(magazineDetails);
        } else {
            magazineDetailsService.updateMagazine(magazineDetails);
        }
        return "redirect:/magazinedetails";
    }

    @PostMapping("/{id}/delete")
    public String deleteMagazineDetails(@PathVariable Long id) {
        magazineDetailsService.delete(id);
        return "redirect:/magazinedetails";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        MagazineDetails magazineDetails = magazineDetailsService.getMagazineById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid magazine Id:" + id));
        model.addAttribute("magazineDetails", magazineDetails);
        return "magazineDetails/details";
    }
}