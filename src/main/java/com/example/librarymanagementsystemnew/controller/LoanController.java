package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Loan;
import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.service.ReadableItemService;
import com.example.librarymanagementsystemnew.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;
    private final ReadableItemService readableItemService;

    public LoanController(LoanService loanService, ReadableItemService readableItemService) {
        this.loanService = loanService;
        this.readableItemService = readableItemService;
    }

    // GET /loan - list all loans
    @GetMapping
    public String listLoans(Model model) {
        model.addAttribute("loans", loanService.findAll());
        return "loan/index";
    }

    // GET /loan/new - show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // Zeigt das heutige Datum im Formular an
        model.addAttribute("loan", new Loan(null, null, LocalDate.now(), new ArrayList<>(), new ArrayList<>())); // Geändert von new Date()
        model.addAttribute("pageTitle", "Create New Loan");
        return "loan/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Loan loan = loanService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid loan Id:" + id));
        model.addAttribute("loan", loan);
        model.addAttribute("pageTitle", "Edit Loan");
        return "loan/form";
    }

    // POST /loan - create new loan (use request params so we don't rely on a no-arg constructor)
    @PostMapping("/save")
    public String saveLoan(@ModelAttribute Loan loan,
                           @RequestParam(value = "dateString", required = false) String dateString,
                           @RequestParam(value = "itemIds", required = false) String itemIds) { // Nimmt Datum als String

        // Parsen des Datums aus dem Formular-Input
        if (dateString != null && !dateString.isEmpty())
            try {
                loan.setDate(LocalDate.parse(dateString)); // Geändert von SimpleDateFormat
            } catch (DateTimeParseException e) {
                System.err.println("Ungültiges Datumsformat: " + dateString);
                loan.setDate(LocalDate.now()); // Fallback
            }
        else
            loan.setDate(LocalDate.now());

        // Parsen der Item-IDs (wird nur beim Erstellen/Bearbeiten benötigt)
        List<ReadableItem> items = new ArrayList<>();
        if (itemIds != null && !itemIds.isEmpty()) {
            String[] parts = itemIds.split(",");
            for (String p : parts) {
                String trimmedId = p.trim();
                if (!trimmedId.isEmpty())
                    // Beim Speichern holen wir das *echte* Item, anstatt ein Platzhalter zu erstellen
                    readableItemService.findById(trimmedId).ifPresent(items::add);
            }
        }
        loan.setItems(items);
        // Reservierungen werden hier vereinfacht
        if(loan.getReservations() == null)
            loan.setReservations(new ArrayList<>());


        if (loan.getId() == null || loan.getId().isEmpty())
            loanService.create(loan);
        else
            loanService.update(loan);
        return "redirect:/loan";
    }

    // POST /loan/{id}/delete - delete loan
    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable String id) {
        loanService.delete(id);
        return "redirect:/loan";
    }
}
