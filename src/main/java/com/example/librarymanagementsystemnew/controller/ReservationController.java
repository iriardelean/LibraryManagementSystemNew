package com.example.librarymanagementsystemnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReservationController {

    @GetMapping("/reservation")
    @ResponseBody
    public String sayReservation() {
        return "Die Anwendung funktioniert! (Reservation)";
    }
}

