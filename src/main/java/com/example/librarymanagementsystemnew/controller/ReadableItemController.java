package com.example.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReadableItemController {

    @GetMapping("/readableitem")
    @ResponseBody
    public String sayReadableItem() {
        return "Die Anwendung funktioniert! (ReadableItem)";
    }
}

