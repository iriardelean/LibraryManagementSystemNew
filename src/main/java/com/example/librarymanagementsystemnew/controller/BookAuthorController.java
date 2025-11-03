package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookAuthorController {

    @GetMapping("/bookauthor")
    @ResponseBody
    public String sayBookAuthor() {
        return "Die Anwendung funktioniert! (BookAuthor)";
    }
}

