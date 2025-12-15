package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Loan date is required")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull(message = "Member is required")
    private Member member;

    @ManyToMany
    @JoinTable(
            name = "loan_items",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @NotEmpty(message = "At least one item must be loaned")
    private List<ReadableItem> items = new ArrayList<>();

    public Loan() {}

    // Getters and Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public List<ReadableItem> getItems() { return items; }
    public void setItems(List<ReadableItem> items) { this.items = items; }
}