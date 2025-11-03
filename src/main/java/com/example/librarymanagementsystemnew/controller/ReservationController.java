package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Date;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "reservation/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation(null, null, null, new Date()));
        return "reservation/form";
    }

    @PostMapping
    public String createReservation(@RequestParam String memberId,
                                    @RequestParam String readableItemId,
                                    @RequestParam(required = false) String date) {
        Date parsedDate = null;
        if (date != null && !date.isEmpty()) {
            try {
                parsedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (Exception ignored) {
            }
        }

        Reservation reservation = new Reservation(UUID.randomUUID().toString(), memberId, readableItemId, parsedDate);
        reservationService.create(reservation);
        return "redirect:/reservation";
    }

    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable String id) {
        reservationService.delete(id);
        return "redirect:/reservation";
    }
}
