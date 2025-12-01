package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.Publication;
import com.example.librarymanagementsystemnew.repository.PublicationRepository;
import com.example.librarymanagementsystemnew.service.ReadableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/readableitem")
public class ReadableItemController {

    private final ReadableItemService readableItemService;
    private final PublicationRepository publicationRepository;

    public ReadableItemController(ReadableItemService readableItemService, PublicationRepository publicationRepository) {
        this.readableItemService = readableItemService;
        this.publicationRepository = publicationRepository;
    }

    @GetMapping
    public String listReadableItems(Model model) {
        model.addAttribute("readableItems", readableItemService.getAllReadableItem());
        return "readableItem/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("readableItem", new ReadableItem());
        model.addAttribute("pageTitle", "Create New Readable Item");
        return "readableItem/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ReadableItem item = readableItemService.getReadableItemById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        model.addAttribute("readableItem", item);
        model.addAttribute("pageTitle", "Edit Readable Item");
        return "readableItem/form";
    }

    @PostMapping("/save")
    public String saveReadableItem(@ModelAttribute ReadableItem readableItem,
                                   @RequestParam("publicationId") Long publicationId) {

        Publication pub = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Publication ID"));
        readableItem.setPublication(pub);

        if (readableItem.getId() == null) {
            readableItemService.createReadableItem(readableItem);
        } else {
            readableItemService.updateReadableItem(readableItem);
        }
        return "redirect:/readableitem";
    }

    @PostMapping("/{id}/delete")
    public String deleteReadableItem(@PathVariable Long id) {
        readableItemService.deleteReadableItem(id);
        return "redirect:/readableitem";
    }
}