package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthorController {

    @GetMapping("/author")
    @ResponseBody
    public String sayAuthor() {
        return "Die Anwendung funktioniert! (Author)";
    }
}