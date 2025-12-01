package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private LocalDate date;
    private List<Reservation> reservations;
    private List<ReadableItem> items;

    public Loan(Long id, String memberId, LocalDate date, List<Reservation> reservations, List<ReadableItem> items) {
        this.id = id;
        this.memberId = memberId;
        this.date = date;
        this.reservations = reservations;
        this.items = items;
    }

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
