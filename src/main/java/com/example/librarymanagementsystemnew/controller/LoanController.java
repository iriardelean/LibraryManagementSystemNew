package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Loan;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.service.ReadableItemService;
import com.example.librarymanagementsystemnew.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String listLoans(Model model) {
        model.addAttribute("loans", loanService.getAllLoans());
        return "loan/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("loan", new Loan());
        model.addAttribute("pageTitle", "Create New Loan");
        return "loan/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Loan loan = loanService.getLoanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid loan Id:" + id));
        model.addAttribute("loan", loan);
        model.addAttribute("pageTitle", "Edit Loan");
        return "loan/form";
    }

    @PostMapping("/save")
    public String saveLoan(@ModelAttribute Loan loan,
                           @RequestParam(value = "dateString", required = false) String dateString,
                           @RequestParam(value = "itemIds", required = false) String itemIds) {

        if (dateString != null && !dateString.isEmpty())
            try {
                loan.setDate(LocalDate.parse(dateString));
            } catch (DateTimeParseException e) {
                System.err.println("Ungültiges Datumsformat: " + dateString);
                loan.setDate(LocalDate.now()); // Fallback
            }
        else
            loan.setDate(LocalDate.now());

        List<ReadableItem> items = new ArrayList<>();
        if (itemIds != null && !itemIds.isEmpty()) {
            String[] parts = itemIds.split(",");
            for (String p : parts) {
                String trimmedId = p.trim();
                if (!trimmedId.isEmpty())
                    readableItemService.(trimmedId).ifPresent(items::add);
            }
        }
        loan.setItems(items);
        if(loan.getReservations() == null)
            loan.setReservations(new ArrayList<>());


        if (loan.getId() == null) {
            loanService.createLoan(loan);
        } else
            loanService.updateLoan(loan);
        return "redirect:/loan";
    }

    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable Long id) {
        loanService.delete(id);
        return "redirect:/loan";
    }
}
