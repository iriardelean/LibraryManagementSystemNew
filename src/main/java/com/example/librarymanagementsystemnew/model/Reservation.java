package com.example.librarymanagementsystemnew.model;

import java.time.LocalDate;

public class Reservation {
    private String id;
    private String memberId;
    private String readableItemId;
    private LocalDate date;
    private ReservationStatus reservationStatus;

    public Reservation(String id, String memberId, String readableItemId, LocalDate date) {
        this.id = id;
        this.memberId = memberId;
        this.readableItemId = readableItemId;
        this.date = date;
        this.reservationStatus = ReservationStatus.ACTIVE;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getReadableItemId() {
        return this.readableItemId;
    }

    public void setReadableItemId(String readableItemId) {
        this.readableItemId = readableItemId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ReservationStatus getStatus() {
        return this.reservationStatus;
    }

    public void setStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

}
