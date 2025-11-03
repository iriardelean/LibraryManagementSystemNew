package com.example.librarymanagementsystemnew.model;

import java.util.Date;

public class Reservation {
    private String Id;
    private String memberId;
    private String ReadableItemId;
    private Date date;
    private ReservationStatus reservationStatus;

    public Reservation(String id, String memberId, String readableitemId, Date date) {
        Id = id;
        this.memberId = memberId;
        ReadableItemId = readableitemId;
        this.date = date;
        this.reservationStatus = ReservationStatus.ACTIVE;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getReadableitemId() {
        return ReadableItemId;
    }

    public void setReadableitemId(String readableitemId) {
        ReadableItemId = readableitemId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReservationStatus getStatus() {
        return reservationStatus;
    }

    public void setStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

}
