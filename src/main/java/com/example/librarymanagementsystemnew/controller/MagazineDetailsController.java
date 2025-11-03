package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MagazineDetailsController {

    @GetMapping("/magazinedetails")
    @ResponseBody
    public String sayMagazineDetails() {
        return "Die Anwendung funktioniert! (MagazineDetails)";
    }
}

