package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LibraryController {

    @GetMapping("/library")
    @ResponseBody
    public String sayLibrary() {
        return "Die Anwendung funktioniert! (Library)";
    }
}

