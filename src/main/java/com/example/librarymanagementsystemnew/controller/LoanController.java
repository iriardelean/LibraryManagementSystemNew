package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoanController {

    @GetMapping("/loan")
    @ResponseBody
    public String sayLoan() {
        return "Die Anwendung funktioniert! (Loan)";
    }
}

