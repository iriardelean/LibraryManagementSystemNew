package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Loan;
import com.example.librarymanagementsystemnew.service.LoanService;
import com.example.librarymanagementsystemnew.service.MemberService;
import com.example.librarymanagementsystemnew.service.ReadableItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;
    private final MemberService memberService;
    private final ReadableItemService readableItemService;

    public LoanController(LoanService loanService, MemberService memberService, ReadableItemService readableItemService) {
        this.loanService = loanService;
        this.memberService = memberService;
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
        model.addAttribute("members", memberService.getAllMembers()); // Populate Member Dropdown
        model.addAttribute("allItems", readableItemService.getAllReadableItem()); // Populate Item List
        model.addAttribute("pageTitle", "Create New Loan");
        return "loan/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Loan loan = loanService.getLoanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid loan Id:" + id));
        model.addAttribute("loan", loan);
        model.addAttribute("members", memberService.getAllMembers()); // Populate Member Dropdown
        model.addAttribute("allItems", readableItemService.getAllReadableItem()); // Populate Item List
        model.addAttribute("pageTitle", "Edit Loan");
        return "loan/form";
    }

    @PostMapping("/save")
    public String saveLoan(@Valid @ModelAttribute Loan loan, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Reload lists on error
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("allItems", readableItemService.getAllReadableItem());
            model.addAttribute("pageTitle", loan.getId() == null ? "Create New Loan" : "Edit Loan");
            return "loan/form";
        }

        if (loan.getId() == null) {
            loanService.createLoan(loan);
        } else {
            loanService.updateLoan(loan);
        }
        return "redirect:/loan";
    }

    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return "redirect:/loan";
    }
}