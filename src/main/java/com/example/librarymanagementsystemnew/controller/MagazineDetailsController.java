package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.MagazineDetails;
import com.example.librarymanagementsystemnew.service.MagazineDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/magazinedetails")
public class MagazineDetailsController {

    private final MagazineDetailsService magazineDetailsService;

    public MagazineDetailsController(MagazineDetailsService magazineDetailsService) {
        this.magazineDetailsService = magazineDetailsService;
    }

    @GetMapping
    public String listMagazineDetails(Model model) {
        model.addAttribute("magazineDetailsList", magazineDetailsService.findAll());
        return "magazinedetails/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("magazineDetails", new MagazineDetails());
        return "magazinedetails/form";
    }

    @PostMapping
    public String createMagazineDetails(@ModelAttribute MagazineDetails magazineDetails) {
        magazineDetails.setId(UUID.randomUUID().toString());
        magazineDetailsService.create(magazineDetails);
        return "redirect:/magazinedetails";
    }

    @PostMapping("/{id}/delete")
    public String deleteMagazineDetails(@PathVariable String id) {
        magazineDetailsService.delete(id);
        return "redirect:/magazinedetails";
    }
}

