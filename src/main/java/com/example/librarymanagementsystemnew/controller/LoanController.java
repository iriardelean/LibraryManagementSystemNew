package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Loan;
import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
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
        // provide an empty template model for the form (not used for binding in POST)
        model.addAttribute("loan", new Loan(null, null, null, new ArrayList<>(), new ArrayList<>()));
        return "loan/form";
    }

    // POST /loan - create new loan (use request params so we don't rely on a no-arg constructor)
    @PostMapping
    public String createLoan(@RequestParam String memberId,
                             @RequestParam(required = false) String date,
                             @RequestParam(required = false) String itemIds) {
        Date parsedDate = null;
        if (date != null && !date.isEmpty()) {
            try {
                parsedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (Exception ignored) {
            }
        }

        List<Reservation> reservations = new ArrayList<>();
        List<ReadableItem> items = new ArrayList<>();
        // parse comma-separated item ids and create ReadableItem placeholders
        if (itemIds != null && !itemIds.isEmpty()) {
            String[] parts = itemIds.split(",");
            for (String p : parts) {
                String trimmed = p.trim();
                if (!trimmed.isEmpty()) {
                    // create a lightweight ReadableItem using the provided id
                    items.add(new ReadableItem(trimmed, null, null));
                }
            }
        }

        Loan loan = new Loan(UUID.randomUUID().toString(), memberId, parsedDate, reservations, items);
        loanService.create(loan);
        return "redirect:/loan";
    }

    // POST /loan/{id}/delete - delete loan
    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable String id) {
        loanService.delete(id);
        return "redirect:/loan";
    }
}
