package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index", "/home"})
    public String rootIndex(Model model) {
        model.addAttribute("appName", "Library Management");
        return "homepage/index";
    }

    @GetMapping("/format")
    public String rootFormat() {
        return "homepage/format";
    }

    @GetMapping({"/homepage","/homepage/index"})
    public String homepageIndex() {
        return "homepage/index";
    }

    @GetMapping("/homepage/format")
    public String homepageFormat(Model model) {
        return "homepage/format";
    }
}
