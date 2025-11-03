package com.example.librarymanagementsystemnew.model;

import java.util.Date;
import java.util.List;

public class Loan {
    private String Id;
    private String memberId;
    private Date date;
    private List<Reservation> reservations;
    private List<ReadableItem> items;

    public Loan(String id, String memberId, Date date, List<Reservation> reservations, List<ReadableItem> items) {
        Id = id;
        this.memberId = memberId;
        this.date = date;
        this.reservations = reservations;
        this.items = items;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
