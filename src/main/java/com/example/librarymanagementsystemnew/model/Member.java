package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String libraryId;
    private List<Reservation> reservations;
    private List<Loan> loans;

    // new properties
    private String email;
    private LocalDate membershipDate;

    public Member() {
        this.reservations = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public Member(Long id, String name, String libraryId, List<Reservation> reservations, List<Loan> loans) {
        this.id = id;
        this.name = name;
        this.libraryId = libraryId;
        this.reservations = reservations;
        this.loans = loans;
    }

    public Member(Long id, String name, String libraryId, List<Reservation> reservations, List<Loan> loans, String email, LocalDate membershipDate) {
        this.id = id;
        this.name = name;
        this.libraryId = libraryId;
        this.reservations = reservations;
        this.loans = loans;
        this.email = email;
        this.membershipDate = membershipDate;
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getMembershipDate() {
        return this.membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

}
