package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.model.ReservationStatus;
import com.example.librarymanagementsystemnew.service.MemberService;
import com.example.librarymanagementsystemnew.service.ReadableItemService;
import com.example.librarymanagementsystemnew.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final ReadableItemService readableItemService;

    public ReservationController(ReservationService reservationService, MemberService memberService, ReadableItemService readableItemService) {
        this.reservationService = reservationService;
        this.memberService = memberService;
        this.readableItemService = readableItemService;
    }

    @GetMapping
    public String listReservations(Model model,
                                   @RequestParam(required = false) String memberName,
                                   @RequestParam(required = false) String itemTitle,
                                   @RequestParam(required = false) LocalDate minDate,
                                   @RequestParam(required = false) LocalDate maxDate,
                                   @RequestParam(required = false) String status, // CHANGED: Accept String to handle "" safely
                                   @RequestParam(defaultValue = "id") String sortField,
                                   @RequestParam(defaultValue = "asc") String sortDir) {

        // Safely convert String to Enum
        ReservationStatus statusEnum = null;
        if (status != null && !status.isEmpty()) {
            try {
                statusEnum = ReservationStatus.valueOf(status);
            } catch (IllegalArgumentException e) {
                // Ignore invalid/empty values, treating them as null (All)
            }
        }

        // Pass the converted Enum to the service
        model.addAttribute("reservations", reservationService.searchReservations(memberName, itemTitle, minDate, maxDate, statusEnum, sortField, sortDir));

        // Pass values back to the view
        model.addAttribute("memberName", memberName);
        model.addAttribute("itemTitle", itemTitle);
        model.addAttribute("minDate", minDate);
        model.addAttribute("maxDate", maxDate);
        model.addAttribute("status", statusEnum); // Pass the Enum back for the dropdown selection
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "reservation/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("readableItems", readableItemService.getAvailableReadableItems());
        model.addAttribute("pageTitle", "Create New Reservation");
        return "reservation/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        model.addAttribute("reservation", reservation);
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("readableItems", readableItemService.getAllReadableItem());
        model.addAttribute("pageTitle", "Edit Reservation");
        return "reservation/form";
    }

    @PostMapping("/save")
    public String saveReservation(@Valid @ModelAttribute Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("readableItems", readableItemService.getAllReadableItem());
            model.addAttribute("pageTitle", reservation.getId() == null ? "Create New Reservation" : "Edit Reservation");
            return "reservation/form";
        }

        if (reservation.getId() == null) {
            reservationService.createReservation(reservation);
        } else {
            reservationService.updateReservation(reservation);
        }
        return "redirect:/reservation";
    }

    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservation";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        model.addAttribute("reservation", reservation);
        return "reservation/details";
    }
}