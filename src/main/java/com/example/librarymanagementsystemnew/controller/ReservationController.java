package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservation());
        return "reservation/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        model.addAttribute("reservation", reservation);
        model.addAttribute("pageTitle", "Edit Reservation");
        return "reservation/form";
    }

    @PostMapping("/save")
    public String saveReservation(@ModelAttribute Reservation reservation,
                                  @RequestParam(value = "dateString", required = false) String dateString) { // Nimmt Datum als String

        if (dateString != null && !dateString.isEmpty())
            try {
                reservation.setDate(LocalDate.parse(dateString));
            } catch (DateTimeParseException e) {
                System.err.println("Ungültiges Datumsformat: " + dateString);
                reservation.setDate(LocalDate.now());
            }
        else
            reservation.setDate(LocalDate.now());


        if (reservation.getId() == null) {
            reservationService.createReservation(reservation);
        } else
            reservationService.updateReservation(reservation);
        return "redirect:/reservation";
    }

    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservation";
    }
}
