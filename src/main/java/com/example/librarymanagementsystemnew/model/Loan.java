package com.example.librarymanagementsystemnew.model;

import java.time.LocalDate;
import java.util.List;

public class Loan {
    private String id;
    private String memberId;
    private LocalDate date;
    private List<Reservation> reservations;
    private List<ReadableItem> items;

    public Loan(String id, String memberId, LocalDate date, List<Reservation> reservations, List<ReadableItem> items) {
        this.id = id;
        this.memberId = memberId;
        this.date = date;
        this.reservations = reservations;
        this.items = items;
    }

    public Loan() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<ReadableItem> getItems() {
        return items;
    }

    public void setItems(List<ReadableItem> items) {
        this.items = items;
    }


}
