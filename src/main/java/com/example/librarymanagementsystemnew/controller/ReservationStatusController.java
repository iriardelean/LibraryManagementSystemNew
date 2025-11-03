package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReservationStatusController {

    @GetMapping("/reservationstatus")
    @ResponseBody
    public String sayReservationStatus() {
        return "Die Anwendung funktioniert! (ReservationStatus)";
    }
}
