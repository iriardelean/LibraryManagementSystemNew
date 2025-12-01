package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String readableItemId;
    private LocalDate date;
    private ReservationStatus status;

    public Reservation(Long id, String memberId, String readableItemId, LocalDate date) {
        this.id = id;
        this.memberId = memberId;
        this.readableItemId = readableItemId;
        this.date = date;
        this.status = ReservationStatus.ACTIVE;
    }

    public Reservation() {
        this.status = ReservationStatus.ACTIVE;
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

    public String getReadableItemId() {
        return readableItemId;
    }

    public void setReadableItemId(String readableItemId) {
        this.readableItemId = readableItemId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus reservationStatus) {
        this.status = reservationStatus;
    }

}
