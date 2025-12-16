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

import java.time.LocalDate;

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
    public String listLoans(Model model,
                            @RequestParam(required = false) String memberName,
                            @RequestParam(required = false) LocalDate minDate,
                            @RequestParam(required = false) LocalDate maxDate,
                            @RequestParam(defaultValue = "id") String sortField,
                            @RequestParam(defaultValue = "asc") String sortDir) {

        model.addAttribute("loans", loanService.searchLoans(memberName, minDate, maxDate, sortField, sortDir));

        model.addAttribute("memberName", memberName);
        model.addAttribute("minDate", minDate);
        model.addAttribute("maxDate", maxDate);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "loan/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("loan", new Loan());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("allItems", readableItemService.getAvailableReadableItems());
        model.addAttribute("pageTitle", "Create New Loan");
        return "loan/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Loan loan = loanService.getLoanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid loan Id:" + id));
        model.addAttribute("loan", loan);
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("allItems", readableItemService.getAllReadableItem());
        model.addAttribute("pageTitle", "Edit Loan");
        return "loan/form";
    }

    @PostMapping("/save")
    public String saveLoan(@Valid @ModelAttribute Loan loan, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberService.getAllMembers());

            if (loan.getId() == null) {
                model.addAttribute("allItems", readableItemService.getAvailableReadableItems());
            } else {
                model.addAttribute("allItems", readableItemService.getAllReadableItem());
            }

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

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        Loan loan = loanService.getLoanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid loan Id:" + id));
        model.addAttribute("loan", loan);
        return "loan/details";
    }
}