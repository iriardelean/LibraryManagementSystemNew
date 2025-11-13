package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.ReadableItemStatus;
import com.example.librarymanagementsystemnew.service.ReadableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/readableitem")
public class ReadableItemController {

    private final ReadableItemService readableItemService;

    public ReadableItemController(ReadableItemService readableItemService) {
        this.readableItemService = readableItemService;
    }

    @GetMapping
    public String listReadableItems(Model model) {
        model.addAttribute("readableItems", readableItemService.findAll());
        return "readableItem/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("readableItem", new ReadableItem(null, "", ""));
        model.addAttribute("pageTitle", "Create New Readable Item");
        return "readableItem/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        ReadableItem item = readableItemService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid readable item Id:" + id));
        model.addAttribute("readableItem", item);
        model.addAttribute("pageTitle", "Edit Readable Item");
        return "readableItem/form";
    }

    @PostMapping("/save")
    public String saveReadableItem(@ModelAttribute ReadableItem readableItem,
                                   @RequestParam(value = "status", required = false) String status) {
        // Ensure status is set (form returns a string)
        if (status != null && !status.isEmpty()) {
            try {
                readableItem.setStatus(ReadableItemStatus.valueOf(status));
            } catch (IllegalArgumentException e) {
                // Unknown status string -> fallback
                readableItem.setStatus(ReadableItemStatus.AVAILABLE);
            }
        } else if (readableItem.getStatus() == null) {
            readableItem.setStatus(ReadableItemStatus.AVAILABLE);
        }

        if (readableItem.getId() == null || readableItem.getId().isEmpty()) {
            readableItem.setId(UUID.randomUUID().toString());
            readableItemService.create(readableItem);
        } else {
            readableItemService.update(readableItem);
        }
        return "redirect:/readableitem";
    }

    @PostMapping("/{id}/delete")
    public String deleteReadableItem(@PathVariable String id) {
        readableItemService.delete(id);
        return "redirect:/readableitem";
    }
}
