package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublicationController {

    @GetMapping("/publication")
    @ResponseBody
    public String sayPublication() {
        return "Die Anwendung funktioniert! (Publication)";
    }
}

