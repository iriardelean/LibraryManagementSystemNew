package com.example.librarymanagementsystemnew.model;

import java.time.LocalDate;

public class Reservation {
    private String id;
    private String memberId;
    private String readableItemId;
    private LocalDate date;
    private ReservationStatus status;

    public Reservation(String id, String memberId, String readableItemId, LocalDate date) {
        this.id = id;
        this.memberId = memberId;
        this.readableItemId = readableItemId;
        this.date = date;
        this.status = ReservationStatus.ACTIVE;
    }

    public Reservation() {
        this.status = ReservationStatus.ACTIVE;
    }

    public String getId() {
        return id;
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
