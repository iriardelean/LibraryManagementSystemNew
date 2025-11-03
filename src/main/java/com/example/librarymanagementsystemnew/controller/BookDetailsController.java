package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookDetailsController {

    @GetMapping("/bookdetails")
    @ResponseBody
    public String sayBookDetails() {
        return "Die Anwendung funktioniert! (BookDetails)";
    }
}