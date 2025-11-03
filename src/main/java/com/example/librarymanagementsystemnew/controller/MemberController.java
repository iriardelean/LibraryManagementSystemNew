package com.example.librarymanagementsystemnew.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @GetMapping("/member")
    @ResponseBody
    public String sayMember() {
        return "Die Anwendung funktioniert! (Member)";
    }
}
