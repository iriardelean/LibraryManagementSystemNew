package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.ReadableItemStatus;
import com.example.librarymanagementsystemnew.repository.PublicationRepository;
import com.example.librarymanagementsystemnew.service.ReadableItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String listReadableItems(Model model,
                                    @RequestParam(required = false) String barcode,
                                    @RequestParam(required = false) String status, // Changed to String to handle "" (empty) safely
                                    @RequestParam(required = false) String publicationTitle,
                                    @RequestParam(defaultValue = "id") String sortField,
                                    @RequestParam(defaultValue = "asc") String sortDir) {

        // Convert String status to Enum safely
        ReadableItemStatus statusEnum = null;
        if (status != null && !status.isEmpty()) {
            try {
                statusEnum = ReadableItemStatus.valueOf(status);
            } catch (IllegalArgumentException e) {
                // Ignore invalid status values, treat as null (All)
            }
        }

        // Pass the Enum to the service
        model.addAttribute("readableItems", readableItemService.searchReadableItems(barcode, statusEnum, publicationTitle, sortField, sortDir));

        // Pass values back to the view
        model.addAttribute("barcode", barcode);
        model.addAttribute("status", statusEnum); // Pass the Enum back so the dropdown selects correctly
        model.addAttribute("publicationTitle", publicationTitle);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "readableItem/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("readableItem", new ReadableItem());
        model.addAttribute("publications", publicationRepository.findAll());
        model.addAttribute("pageTitle", "Create New Readable Item");
        return "readableItem/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ReadableItem item = readableItemService.getReadableItemById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        model.addAttribute("readableItem", item);
        model.addAttribute("publications", publicationRepository.findAll());
        model.addAttribute("pageTitle", "Edit Readable Item");
        return "readableItem/form";
    }

    @PostMapping("/save")
    public String saveReadableItem(@Valid @ModelAttribute ReadableItem readableItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("publications", publicationRepository.findAll());
            model.addAttribute("pageTitle", readableItem.getId() == null ? "Create New Readable Item" : "Edit Readable Item");
            return "readableItem/form";
        }

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

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        ReadableItem readableItem = readableItemService.getReadableItemById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        model.addAttribute("readableItem", readableItem);
        return "readableItem/details";
    }
}